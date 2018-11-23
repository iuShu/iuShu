package org.iushu.config;

import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * A debug class that no influence to main framework.
 *
 * Created by iuShu on 18-10-31
 */
public class SimpleProgress {

    public static void main(String[] args) throws Exception {

//        parseProps();

//        parseXml();

//        nio();

//        commonio();

//        log4j();

//        customWatcher();

        loadClass();
    }

    private static void parseProps() throws Exception {
        String path = "/media/iushu/120bd41f-5ddb-45f2-9233-055fdc3aca07/workplace-idea/iushu/config/target/classes/server.properties";
        InputStream is = new FileInputStream(path);

        Properties props = new Properties();
        props.load(is);

        Enumeration<?> keys = props.propertyNames();
        while (keys.hasMoreElements())
            System.out.println(keys.nextElement().toString());

        is.close();
    }

    private static void parseXml() throws Exception {
        String path = "/media/iushu/120bd41f-5ddb-45f2-9233-055fdc3aca07/workplace-idea/iushu/config/target/classes/dom.xml";
        InputStream is = new FileInputStream(path);

        SAXReader reader = new SAXReader();
        Document document = reader.read(is);
        Iterator<Node> it = document.nodeIterator();
        while (it.hasNext()) {
            if (!(it.next() instanceof Element))
                continue;
        }
    }

    /**
     * WatchService could receives two events in one modification because of the invisible tmp files.
     */
    private static void nio() {
        try {
            String root = SimpleProgress.class.getClassLoader().getResource("").getPath();
            FileSystem fileSystem = FileSystems.getDefault();
            Path path = fileSystem.getPath(root);
            WatchService watchService = fileSystem.newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
                System.out.println(">>> start");
                WatchKey watchKey = watchService.take();
                for (WatchEvent<?> watchEvent : watchKey.pollEvents()) // process events
                    System.out.println(watchEvent.context() + ": " + watchEvent.kind().name());

                boolean isValid = watchKey.reset();
                if (!isValid) { // object is no longer registered
                    System.out.println("cancel registration: " + watchKey);
                    break;
                }

                System.out.println(">>> end");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void commonio() {

    }

    private static void log4j() {
//        String root = SimpleProgress.class.getClassLoader().getResource(".").getPath();
        PropertyConfigurator.configureAndWatch("server.properties");
    }

    private static void customWatcher() {
        String root = SimpleProgress.class.getClassLoader().getResource(".").getPath();
        Thread watcher = new Thread(() -> {
            try {
                while (true) {
                    TimeUnit.SECONDS.sleep(3);
                    File file = new File(root + "server.properties");
                    System.out.println(file.lastModified());
                }
            } catch (Exception e) {}
        });
        watcher.start();
    }

    private static void loadClass() throws Exception {
        ClassLoader classLoader = SimpleProgress.class.getClassLoader(); // AppClassLoader
        Class<?> clazz = classLoader.loadClass("org.iushu.config.zoo.ServerConfig");
        Object instance = clazz.newInstance();
        System.out.println("before: " + instance);

        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("port", clazz);
        Class<?> propertyType = propertyDescriptor.getPropertyType();

        PropertyEditor propertyEditor = PropertyEditorManager.findEditor(propertyType);
        propertyEditor.setAsText("5889");
        Object val = propertyEditor.getValue();

        propertyDescriptor.getWriteMethod().invoke(instance, val);

        System.out.println("after: " + instance);
    }

}
