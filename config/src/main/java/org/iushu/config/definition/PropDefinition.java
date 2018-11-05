package org.iushu.config.definition;

import org.iushu.config.document.Document;
import org.iushu.config.document.resolver.Resolver;
import org.iushu.config.resource.Resource;

/**
 * Created by iuShu on 18-10-25
 */
public class PropDefinition extends AbstractDefinition {

    public PropDefinition(String name, Document document) {
        super(name, document);
    }

    @Override
    public void resolve(Resolver resolver) {

    }
}
