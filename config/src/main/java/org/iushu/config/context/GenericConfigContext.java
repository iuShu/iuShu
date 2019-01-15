package org.iushu.config.context;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.iushu.config.context.hotswap.ConfigurationWatcher;
import org.iushu.config.context.hotswap.WatcherListener;
import org.iushu.config.document.Document;
import org.iushu.config.document.property.Tokenizer;
import org.iushu.config.context.hotswap.WatchEvent;
import org.iushu.config.resource.Resource;
import org.iushu.config.resource.scanner.ResourceScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Generic implementation
 *
 * GenericConfigContext can loading configuration and store them, also provides
 * #getValue() method to get value.
 * Hotswap is also support in this implementation.
 *
 * Created by iuShu on 18-11-16
 */
public class GenericConfigContext implements WatchableConfigContext, Closeable {

    private static Logger logger = LoggerFactory.getLogger(CONFIGURATION_LOGGER);

    private Thread watcher;
    private ResourceScanner configScanner;
    private Map<String, Document> documentMap;

    private List<WatcherListener> listeners;

    public GenericConfigContext(ResourceScanner configScanner) {
        this.configScanner = configScanner;
        listeners = Lists.newLinkedList();
    }

    @Override
    public void load() {
        Preconditions.checkArgument(documentMap == null, "Configuration already loaded.");
        logger.info("[context] begin load");

        Map<String, Document> map = Maps.newHashMap();
        try {
            List<Resource> resources = configScanner.scan();
            for (Resource resource : resources) {
                Document document = resource.deliver();
                document.resolve();
                map.put(document.getName(), document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.documentMap = new ConcurrentHashMap<>(map);

        // remove automatic open watching.
//        watching();

        logger.info("[context] end load");
    }

    @Override
    public Document getDocument(String configName) {
        return documentMap.get(configName);
    }

    @Override
    public Object getValue(String configName, String configKey) {
        if (documentMap == null || documentMap.isEmpty())
            return null;

        Document document = documentMap.get(configName);
        if (document == null || document.getRepository() == null)
            return null;
        return document.getRepository().getValue(Tokenizer.create(configKey));
    }

    @Override
    public Set<Document> documents() {
        return new HashSet<>(documentMap.values());
    }

    @Override
    public ResourceScanner scanner() {
        return configScanner;
    }

    @Override
    public void onChange(WatchEvent watchEvent, Document document) throws Exception {
        switch (watchEvent) {
            case MODIFIED:
                reload(document);
                break;
            case DELETED:
                remove(document);
                break;
        }
        notifyListener(watchEvent, document);
    }

    @Override
    public void watching() {
        if (watcher != null) // already watching
            return;

        this.watcher = new Thread(new ConfigurationWatcher(this));
        watcher.setDaemon(false);
        watcher.start();
        logger.info("[context] start watching");
    }

    @Override
    public void notifyListener(WatchEvent event, Document document) {
        for (WatcherListener listener : listeners)
            listener.afterChange(event, document);
    }

    @Override
    public void addListener(WatcherListener listener) {
        Preconditions.checkArgument(listener != null);
        listeners.add(listener);
    }

    protected void reload(Document document) throws Exception {
        Document old = documentMap.get(document.getName());
        if (old == null)
            return;

        document.resolve();
        documentMap.put(document.getName(), document);
        logger.info("[context] watcher.reload: " + document.getName());
    }

    protected void remove(Document document) {
        documentMap.remove(document.getName());
        logger.info("[context] watcher.remove: " + document.getName());
    }

    @Override
    public void close() throws IOException {
        configScanner = null;
        if (documentMap != null)
            documentMap.clear();
        if (watcher != null)
            watcher.interrupt();
        logger.info("[context] closed, Good bye.");
    }

}
