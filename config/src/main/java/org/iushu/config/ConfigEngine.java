package org.iushu.config;

import org.iushu.config.definition.Definition;
import org.iushu.config.document.Document;
import org.iushu.config.document.property.PropertyRepository;
import org.iushu.config.document.property.Tokenizer;
import org.iushu.config.document.resolver.JdkPropResolver;
import org.iushu.config.document.resolver.Resolver;
import org.iushu.config.resource.Resource;
import org.iushu.config.resource.Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by iuShu on 18-10-23
 */
public class ConfigEngine {

    /**
     * 1. register configuration url
     *      1.1 scan project directories and filters for optional.
     *      1.2 configuration types: props and xml and ...(TODO).
     *      1.3 catch target configurations and store path.
     *
     * 2. resolve configuration
     *      2.1 Configuration is regard as a Document, DOM node as Node.
     *      2.1 core resolver: Resolver.
     *      2.2 complex node resolve...(TODO).
     *
     * 3. store configuration url
     *      3.1 Map ?
     *
     * 3. exclude directors or files
     *
     * 4. hotswap
     *
     * 5. integration with Spring
     *
     * 6. distribution config
     *
     */

    public static void main(String[] args) {

//        ConfigEngine.registerUrl("...").run();

        /*

        // single configuration
        Resource resource = Resources.register("xx/xx/xx.xml"); // file path
        // multiple configuration in directory
        Resource resource = Resources.register("xx/xx/xx/"); // dir path

        Document document = resource.deliver();
        document.resolve();

         */

//        Resource resource = Resources.registerFile("evil");

        single();

//        Resources.autoScan();
    }

    public static void single() {
        List<Resource> resources = Resources.autoScan();
        Resource resource = resources.get(1);
        Document document = resource.deliver();
        Definition definition = document.getDefinition();
        PropertyRepository repository = null;
        try {
            Resolver resolver = new JdkPropResolver();
            repository = definition.resolve(resolver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (repository == null)
            return;

        System.out.println(repository);
    }

}
