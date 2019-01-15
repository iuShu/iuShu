package org.iushu.config.context;

public interface EntityConfigContext extends ConfigContext {

    <T> T getEntity(String configName, Class<T> clazz);

}
