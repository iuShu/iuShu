package org.iushu.config.document.resolver;

import org.iushu.config.document.Document;
import org.iushu.config.document.property.*;

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
    public PropertyRepository resolve(Document document) throws Exception {
        InputStream is = document.open();
        Properties properties = new Properties();
        try {
            properties.load(is);
            DocumentPropertyRepository repository = new DocumentPropertyRepository();
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                Tokenizer key = new Tokenizer(entry.getKey().toString());
                // properties configuration do not have Property.
                PropertyNode propertyNode = ResolverFlow.newPropertyNode(key, null, entry.getValue());
                repository.put(propertyNode);
            }
            return repository;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        return null;
    }
}
