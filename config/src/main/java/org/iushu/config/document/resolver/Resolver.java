package org.iushu.config.document.resolver;

import org.iushu.config.document.Document;
import org.iushu.config.document.property.PropertyRepository;

/**
 * Be responsibility for resolve document and determine the type of Definition.
 *
 * Created by iuShu on 18-10-25
 */
public interface Resolver {

    /**
     * start resolve the given document.
     */
    PropertyRepository resolve(Document document) throws Exception;

}
