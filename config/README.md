###iuShu.Config

Config component is responsible for loading project configurations. The 
current version is support loading xml and properties files. Hotswap is also
available and able to auto-register configuration to the core hotswap class.

Config component apply a uniform approach to locate or access configuration.
It means that no matter the configuration's type is xml or properties, you could
get the value of different configuration's type by a uniform method.

To be support type: yml and ... ...

####User guide: <br>
Config provided two approach to get configuration value.<br>
Suppose that we have a configuration named server.properties and the content is:<br>
<code>
    ## server.properties <br>
    netty.host=localhost <br>
    netty.port=5889 <br>
</code>

> get Value:
    
<code>
    ResourceScanner configScanner = ResourceScanner.getDefault();
    ConfigContext configContext = new GenericConfigContext(configScanner);
    Object value = configContext.getValue("server", "netty.host");    
    // do something ... ...
</code>

> get Entity which injured value to each fields.

<code>
    ## define a configuratio entity like this: <br>
    ## ServerConfig.java
    
    package org.iushu.config.zoo;
    
    @AutoConfig(name = "server") // configuration file name
    public class ServerConfig {
    
        @AutoValue(key = "netty.host")
        private String host;
        
        @AutoValue(key = "netty.port")
        private int port;
        
        // omit getter and setter ...
    }

    ResourceScanner configScanner = ResourceScanner.getDefault();
    ResourceScanner configScanner = ResourceScanner.newScanner().location("org/iushu/config/zoo");
    ConfigContext configContext = new AutowiredConfigContext(configScanner, entityScanner);
    ServerConfig serverConfig = configContext.getEntity("server", ServerConfig.class);
    // do something ... ...
</code>