package org.iushu.config.context;

import org.iushu.config.document.Document;
import org.iushu.config.resource.scanner.ResourceScanner;

import javax.annotation.Nullable;
import java.util.Set;

public interface ConfigContext {

    String CONFIGURATION_LOGGER = "Configuration";

    /**
     * begin loading config and store.
     */
    void load();

    Document getDocument(String configName);

    @Nullable
    Object getValue(String configName, String configKey);

    Set<Document> documents();

    ResourceScanner scanner();

}
