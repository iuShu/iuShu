package org.iushu.config.document.resolver;

import org.iushu.config.document.Document;
import org.iushu.config.document.property.DocumentPropertyRepository;
import org.iushu.config.document.property.PropertyNode;
import org.iushu.config.document.property.ResolverFlow;
import org.iushu.config.document.property.Tokenizer;

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
            DocumentPropertyRepository repository = new DocumentPropertyRepository();
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                Tokenizer key = new Tokenizer(entry.getKey().toString());

                // properties configuration do not have Property, parent and childes.
                PropertyNode propertyNode = ResolverFlow.newPropertyNode(key, null, entry.getValue());

                repository.put(propertyNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
    }
}
