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
    private Map<String, PropertyRepository> propertyRepositoryMap;

    public ApplicationConfigContext(ResourceScanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void load() {
        List<Resource> resources = scanner.scan();
        Map<String, PropertyRepository> map = Maps.newHashMap();
        for (Resource resource : resources) {
            Document document = resource.deliver();
            Definition definition = document.getDefinition();
            try {
                PropertyRepository repository = definition.resolve();
                map.put(repository.getName(), repository);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.propertyRepositoryMap = new ConcurrentHashMap<>(map);
    }

    @Override
    public Object getValue(String configName, String configKey) {
        if (propertyRepositoryMap == null || propertyRepositoryMap.isEmpty())
            return null;

        PropertyRepository repository = propertyRepositoryMap.get(configName);
        if (repository == null)
            return null;
        return repository.getValue(new Tokenizer(configKey));
    }

    @Override
    public void reload() {

    }

    @Override
    public void register() {

    }

}
