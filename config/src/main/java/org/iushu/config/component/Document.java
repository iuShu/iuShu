package org.iushu.config.component;

import java.util.List;

/**
 * Document is a configuration file
 *
 * Created by iuShu on 18-10-25
 */
public interface Document {

    /**
     * @return the DocumentResolver of this document.
     */
    DocumentResolver getResolver();

    /**
     * @return the definition of this document.
     */
    Definition getDefinition();

    /**
     * resolve by DocumentResolver
     */
    void resolve();
}
