package org.iushu.config.context;

import org.iushu.config.document.Document;
import org.iushu.config.resource.scanner.ResourceScanner;

import javax.annotation.Nullable;
import java.util.Set;

public interface ConfigContext {

    String DEFAULT_LOGGER = "Configuration";

    /**
     * begin loading config and store.
     */
    void load();

    Document getDocument(String configName);

    @Nullable
    Object getValue(String configName, String configKey);

    @Nullable
    <T> T getEntity(String configName, Class<T> clazz);

    Set<Document> documents();

    ResourceScanner scanner();

}
