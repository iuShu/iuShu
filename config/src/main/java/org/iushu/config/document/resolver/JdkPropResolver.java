package org.iushu.config.document.resolver;

import org.iushu.config.context.ConfigContext;
import org.iushu.config.document.Document;
import org.iushu.config.document.property.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static Logger logger = LoggerFactory.getLogger(ConfigContext.CONFIGURATION_LOGGER);

    @Override
    public PropertyRepository resolve(Document document) throws Exception {
        logger.debug("[resolver] begin: {}", document.getName());

        InputStream is = document.open();
        Properties properties = new Properties();
        try {
            properties.load(is);
            DocumentPropertyRepository repository = new DocumentPropertyRepository(document.getName());

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                Tokenizer key = new Tokenizer(entry.getKey().toString());

                String rootKey = key.first();
                PropertyNode root = repository.getRootPropertyNode(rootKey);
                if (root == null) {
                    PropertyNode node = ResolverFlow.newPropertyNode(key, null, entry.getValue());
                    repository.put(node);
                }
                else { // root node have existed, merge behind nodes
                    HierarchicalPropertyNode node = repository.matchingDepth(key);
                    key.back(); // correction
                    PropertyNode behind = ResolverFlow.newPropertyNode(key, null, entry.getValue());
                    node.addChild((HierarchicalPropertyNode) behind);
                }
            }

            logger.debug("[resolver]   end: {}", document.getName());
            return repository;
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            is.close();
        }
        return null;
    }

}
