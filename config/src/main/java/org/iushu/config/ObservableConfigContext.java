package org.iushu.config;

public interface ObservableConfigContext extends ConfigContext {

    void reload();

    void register();

}
