package org.iushu.config.document.property;

import java.util.Collection;

/**
 * The property stores the properties of configuration.
 *
 * Created by iuShu on 18-11-5
 */
public interface PropertyRepository {

    /**
     * @return the name of this property getRepository (default from document)
     */
    String getName();

    Collection<String> keys();

    PropertyNode getRootPropertyNode(String key);

    Object getValue(Tokenizer key);

    void put(PropertyNode propertyNode);

    /**
     * e.g.
     *  PropertyRepository contains: server.heartbeat.interval
     *  matchingDepth("server.heartbeat")
     *  will return PropertyRepository[heartbeat] child=[interval] parent=[server]
     *
     * @param key the given key
     * @return the property node where the key is matched to the maximum depth.
     */
    PropertyNode matchingDepth(Tokenizer key);

}
