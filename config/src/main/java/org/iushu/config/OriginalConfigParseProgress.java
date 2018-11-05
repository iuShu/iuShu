package org.iushu.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by iuShu on 18-10-31
 */
public class OriginalConfigParseProgress {

    public static void main(String[] args) throws Exception {

//        parseProps();

        parseXml();

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


    }

}
