package org.iushu.config.definition;

import org.iushu.config.document.Document;
import org.iushu.config.document.property.PropertyRepository;
import org.iushu.config.document.resolver.JdkPropResolver;
import org.iushu.config.document.resolver.Resolver;
import org.iushu.config.resource.Resource;

/**
 * Created by iuShu on 18-10-25
 */
public class DefaultPropertiesDefinition extends AbstractDefinition {

    public DefaultPropertiesDefinition(String name, Document document) {
        super(name, document);
    }

    @Override
    public PropertyRepository resolve() throws Exception {
        return getResolver().resolve(getDocument());
    }

    @Override
    public Resolver getResolver() {
        return new JdkPropResolver();
    }
}
