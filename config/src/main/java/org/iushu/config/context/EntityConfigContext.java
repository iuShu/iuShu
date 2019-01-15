package org.iushu.config.context;

public interface EntityConfigContext {

    <T> T getEntity(String configName, Class<T> clazz);

}
