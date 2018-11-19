package org.iushu.config.document;

import org.iushu.config.definition.Definition;
import org.iushu.config.document.property.PropertyRepository;
import org.iushu.config.resource.Resource;

import java.io.InputStream;

/**
 * Document indicates a configuration file.
 *
 * Created by iuShu on 18-10-25
 */
public interface Document {

    /**
     * @return the name of this document.
     *      default from Resource's name.
     */
    String getName();

    Resource getResource();

    Definition getDefinition();

    /**
     * @return the property which stores all properties of document.
     */
    PropertyRepository getRepository();

    void setRepository(PropertyRepository repository);

    InputStream open() throws Exception;

}
