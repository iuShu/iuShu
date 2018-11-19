package org.iushu.config;

import com.google.common.collect.Maps;
import org.iushu.config.definition.Definition;
import org.iushu.config.document.Document;
import org.iushu.config.document.property.PropertyRepository;
import org.iushu.config.document.property.Tokenizer;
import org.iushu.config.resource.Resource;
import org.iushu.config.resource.scanner.ResourceScanner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by iuShu on 18-11-16
 */
public class ApplicationConfigContext implements ObservableConfigContext {

    private ResourceScanner scanner;
    private Map<String, Document> documentMap;

    public ApplicationConfigContext(ResourceScanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void load() {
        List<Resource> resources = scanner.scan();
        Map<String, Document> map = Maps.newHashMap();
        for (Resource resource : resources) {
            Document document = resource.deliver();
            Definition definition = document.getDefinition();
            try {
                PropertyRepository repository = definition.resolve();
                document.setRepository(repository);
                map.put(document.getName(), document);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.documentMap = new ConcurrentHashMap<>(map);
    }

    @Override
    public Object getValue(String configName, String configKey) {
        if (documentMap == null || documentMap.isEmpty())
            return null;

        Document document = documentMap.get(configName);
        if (document == null || document.getRepository() == null)
            return null;
        return document.getRepository().getValue(new Tokenizer(configKey));
    }

    @Override
    public void reload() {

    }

    @Override
    public void register() {

    }

}
