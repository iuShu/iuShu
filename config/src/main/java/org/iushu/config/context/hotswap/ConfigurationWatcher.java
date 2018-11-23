package org.iushu.config.context.hotswap;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;
import org.iushu.config.context.WatchableConfigContext;
import org.iushu.config.document.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * A component that responsible for watching the configuration document, reload them if changed.
 * Changed operation includes Modified and Deleted, exclude Created.
 *
 * Created by iuShu on 18-11-20
 */
public class ConfigurationWatcher implements Runnable {

    private static Logger logger = LoggerFactory.getLogger("Configuration");

    private long interval = 3000L; // milliseconds

    private Set<Watchable> watchableSet;
    private WatchableConfigContext configContext;

    public ConfigurationWatcher(WatchableConfigContext configContext) {
        String customInterval = System.getProperty("config.watcher.interval");
        if (customInterval != null)
            this.interval = Integer.valueOf(customInterval);

        this.configContext = configContext;
        this.watchableSet = Sets.newConcurrentHashSet();

        for (Document document : configContext.documents())
            register(document);
    }

    @Override
    public void run() {
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        try {
            while (true) {
                stopwatch.start();
                watch();
                stopwatch.stop();
                long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                long diff = interval - elapsed;
                if (diff <= 0)
                    continue;
                TimeUnit.MILLISECONDS.sleep(diff);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void watch() throws Exception {
        Iterator<Watchable> iterator = watchableSet.iterator();
        while (iterator.hasNext()) {
            Watchable watchable = iterator.next();
            if (!watchable.inWatch()) {
                logger.info("[watcher] deleted: {}", watchable.getDocument().getName());
                iterator.remove();
                configContext.onChange(WatchEvent.DELETED, watchable.getDocument());
            }
            else if (watchable.isChanged()) {
                logger.info("[watcher] modified: {}", watchable.getDocument().getName());
                watchable.recordChanged();
                configContext.onChange(WatchEvent.MODIFIED, watchable.getDocument());
            }
        }
    }

    public void register(Document document) {
        Preconditions.checkNotNull(document);
        watchableSet.add(new Watchable(document));
        logger.debug("[watcher] register: {}", document.getName());
    }

}
