package org.iushu.config.definition;

import org.iushu.config.document.Document;
import org.iushu.config.document.property.PropertyRepository;
import org.iushu.config.document.resolver.Dom4jXmlResolver;
import org.iushu.config.document.resolver.Resolver;
import org.iushu.config.resource.Resource;

/**
 * Created by iuShu on 18-10-25
 */
public class DefaultXmlDefinition extends AbstractDefinition {

    public DefaultXmlDefinition(String name, Document document) {
        super(name, document);
    }

    @Override
    public Resolver getResolver() {
        return new Dom4jXmlResolver();
    }

    @Override
    public PropertyRepository resolve() throws Exception {
        return getResolver().resolve(getDocument());
    }
}
