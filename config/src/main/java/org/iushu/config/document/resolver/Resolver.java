package org.iushu.config.document.resolver;

import org.iushu.config.document.Document;

/**
 * Be responsibility for resolve document and determine the type of Definition.
 *
 * Created by iuShu on 18-10-25
 */
public interface Resolver {

    /**
     * start resolve.
     */
    void resolve(Document document) throws Exception;

}
