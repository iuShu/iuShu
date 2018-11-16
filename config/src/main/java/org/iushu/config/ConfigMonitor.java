package org.iushu.config;

import com.google.common.collect.Maps;
import org.iushu.config.definition.Definition;
import org.iushu.config.document.Document;
import org.iushu.config.document.property.PropertyRepository;
import org.iushu.config.resource.Resource;
import org.iushu.config.resource.scanner.ResourceScanner;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Monitor is responsible for manage configuration loading and listening,
 * reloading if anything have changed.
 *
 * Created by iuShu on 18-11-16
 */
public class ConfigMonitor {

    private ResourceScanner scanner;

    public ConfigMonitor(ResourceScanner scanner) {
        this.scanner = scanner;
    }

    public Map<String, PropertyRepository> load() {
        return null;
    }

    public void reload() {

    }

    public void reloadAll() {

    }

}
