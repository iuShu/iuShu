package org.iushu.config.definition;

import org.iushu.config.document.Document;
import org.iushu.config.document.property.PropertyRepository;
import org.iushu.config.document.resolver.Resolver;
import org.iushu.config.resource.Resource;

/**
 * Definition in configuration file,
 *
 * default provides implementation:
 * @see XmlDefinition
 * @see PropDefinition
 *
 * Created by iuShu on 18-10-25
 */
public interface Definition {

    String getName();

    Document getDocument();

    PropertyRepository resolve(Resolver resolver) throws Exception;

}
