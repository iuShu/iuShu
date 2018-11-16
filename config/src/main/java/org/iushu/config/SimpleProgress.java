package org.iushu.config;

import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by iuShu on 18-10-31
 */
public class SimpleProgress {

    public static void main(String[] args) throws Exception {

//        parseProps();

//        parseXml();

//        nio();

//        commonio();

        log4j();

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

    private static void nio() {

    }

    private static void commonio() {

    }

    private static void log4j() {

    }

}
