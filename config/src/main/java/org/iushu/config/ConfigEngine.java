package org.iushu.config;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.iushu.config.definition.Definition;
import org.iushu.config.document.Document;
import org.iushu.config.document.property.PropertyRepository;
import org.iushu.config.document.property.Tokenizer;
import org.iushu.config.resource.Resource;
import org.iushu.config.resource.Resources;
import org.iushu.config.resource.scanner.ResourceScanner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
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

        afterRefactorScanner();
    }

    public static void singleProps() {
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

    public static void singleXml() {
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

    public static void afterRefactorScanner() {
        ResourceScanner scanner = ResourceScanner.location("").excludeFileName("log4j");
        ConfigContext configContext = new ApplicationConfigContext(scanner);
        configContext.load();

        Object value = configContext.getValue("database", "configuration.properties.property#name=username");
        System.out.println(value);
    }

}
