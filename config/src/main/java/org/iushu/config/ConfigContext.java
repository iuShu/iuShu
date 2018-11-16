package org.iushu.config;

public interface ConfigContext {

    /**
     * begin loading config and store.
     */
    void load();

    /**
     * @Nullable
     * @param configName
     * @param configKey
     * @return
     */
    Object getValue(String configName, String configKey);

}
