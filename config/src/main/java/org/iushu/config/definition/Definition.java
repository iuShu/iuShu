package org.iushu.config.definition;

import org.iushu.config.document.Document;
import org.iushu.config.document.property.PropertyRepository;
import org.iushu.config.document.resolver.Resolver;

/**
 * Definition in configuration file,
 *
 * default provides implementation:
 * @see DefaultXmlDefinition
 * @see DefaultPropertiesDefinition
 *
 * Created by iuShu on 18-10-25
 */
public interface Definition {

    String getName();

    Document getDocument();

    Resolver getResolver();

    PropertyRepository resolve() throws Exception;

}
