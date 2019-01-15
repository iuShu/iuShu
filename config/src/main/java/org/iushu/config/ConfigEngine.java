package org.iushu.config;

import org.apache.commons.lang3.StringUtils;
import org.iushu.config.context.AutowiredConfigContext;
import org.iushu.config.context.EntityConfigContext;
import org.iushu.config.context.GenericConfigContext;
import org.iushu.config.context.ConfigContext;
import org.iushu.config.definition.Definition;
import org.iushu.config.document.Document;
import org.iushu.config.document.property.PropertyRepository;
import org.iushu.config.document.property.Tokenizer;
import org.iushu.config.resource.Resource;
import org.iushu.config.resource.Resources;
import org.iushu.config.resource.scanner.ResourceScanner;
import org.iushu.config.zoo.DatabaseEnvironment;
import org.iushu.config.zoo.ServerConfig;

import java.util.List;

/**
 * A debug class that no influence to main framework.
 *
 * Created by iuShu on 18-10-23
 */
public class ConfigEngine {

    public static void main(String[] args) {

//        ConfigEngine.registerUrl("...").run();

        /*

        // singleProps configuration
        Resource resource = Resources.register("xx/xx/xx.xml"); // file path
        // multiple configuration in directory
        Resource resource = Resources.register("xx/xx/xx/"); // dir path

        Document document = resource.deliver();
        document.resolve();

         */

//        Resource resource = Resources.registerFile("evil");

//        singleProps();

//        singleXml();

//        afterRefactorScanner();

//        genericConfigContext();

        autowiredConfigContext();

    }

    private static void singleProps() {
        List<Resource> resources = Resources.autoScan();
        Resource resource = resources.get(1);
        Document document = resource.deliver();
        Definition definition = document.getDefinition();
        PropertyRepository repository = null;
        try {
            repository = definition.resolve();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (repository == null)
            return;

        System.out.println(repository.getValue(new Tokenizer("server.heartbeat.interval")));
        System.out.println(repository.getValue(new Tokenizer("server.heartbeat.offline.valve")));
        System.out.println(repository.getValue(new Tokenizer("netty.host")));
        System.out.println(repository.getValue(new Tokenizer("netty.port")));
        System.out.println(repository.getValue(new Tokenizer("netty.throughput.watermark")));
        System.out.println(repository.getValue(new Tokenizer("a.b.c.d.e.f")));
        System.out.println(repository.getValue(new Tokenizer("a.b.c.d.cat")));
        System.out.println(repository.getValue(new Tokenizer("a.b.c.dwell")));
    }

    private static void singleXml() {
        List<Resource> resources = Resources.autoScan();
        Resource resource = resources.get(0);
        Document document = resource.deliver();
        Definition definition = document.getDefinition();
        PropertyRepository repository = null;
        try {
            repository = definition.resolve();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (repository == null)
            return;

        System.out.println(repository.getValue(Tokenizer.create("configuration.import")));
        System.out.println(repository.getValue(Tokenizer.create("configuration.environment.connection")));
        System.out.println(repository.getValue(Tokenizer.create("configuration.properties.property#name=username")));
    }

    private static void afterRefactorScanner() {
        ResourceScanner scanner = ResourceScanner.getDefault().excludeFileName("log4j");
        ConfigContext configContext = new GenericConfigContext(scanner);
        configContext.load();

        Object value = configContext.getValue("database", "configuration.properties.property#name=username");
        System.out.println(value);
    }

    private static void genericConfigContext() {
        ResourceScanner configScanner = ResourceScanner.getDefault().excludeFileName("log4j");
        ConfigContext configContext = new GenericConfigContext(configScanner);
        configContext.load();

        Object value = configContext.getValue("server", "server.heartbeat.interval");
        System.out.println("value: " + value);
    }

    private static void autowiredConfigContext() {
        ResourceScanner configScanner = ResourceScanner.getDefault().excludeFileName("log4j");
        ResourceScanner entityScanner = ResourceScanner.newScanner().location("org/iushu/config/zoo/");
        EntityConfigContext configContext = new AutowiredConfigContext(configScanner, entityScanner);
        configContext.load();

        DatabaseEnvironment databaseEnvironment = configContext.getEntity("database", DatabaseEnvironment.class);
        System.out.println(databaseEnvironment);
        ServerConfig serverConfig = configContext.getEntity("server", ServerConfig.class);
        System.out.println(serverConfig);
    }
}
