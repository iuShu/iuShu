package org.iushu.config.document.property;

import java.util.Collection;

/**
 * The property stores the properties of configuration.
 *
 * Created by iuShu on 18-11-5
 */
public interface PropertyRepository {

    /**
     * @return the name of this property repository (default from document)
     */
    String getName();

    Collection<String> keys();

    Object getValue(Tokenizer key);

    void put(PropertyNode propertyNode);
}
