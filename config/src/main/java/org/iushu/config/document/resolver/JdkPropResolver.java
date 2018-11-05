package org.iushu.config.document.resolver;

import org.iushu.config.document.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Use JDK java.util.Properties to resolver .properties configuration.
 * A default properties configuration resolver.
 *
 * Created by iuShu on 18-11-5
 */
public class JdkPropResolver implements Resolver {

    @Override
    public void resolve(Document document) throws Exception {
        InputStream is = document.open();
        Properties properties = new Properties();
        try {
            properties.load(is);
//            for (Map.Entry<Object, Object> entry : properties.entrySet())


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
    }
}
