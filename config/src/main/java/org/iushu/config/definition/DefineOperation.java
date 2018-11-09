package org.iushu.config.definition;

import org.iushu.config.document.Document;

/**
 * According the document to identity the definition.
 * Interface can provides flexible custom functionality.
 *
 * Created by iuShu on 18-11-2
 */
public interface DefineOperation {

    Definition define(Document document);
}
