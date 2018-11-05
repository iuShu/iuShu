package org.iushu.config.document;

import org.iushu.config.definition.Definition;
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

    InputStream open() throws Exception;

}
