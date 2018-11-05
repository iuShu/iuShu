package org.iushu.config.document.repository;

import org.apache.commons.lang3.StringUtils;

/**
 * A simple key-value pair property
 *
 * Created by iuShu on 18-11-5
 */
public class KeyValueProperty implements Property {

    private PropertyNode propertyNode;
    private String key;
    private Object value;

    public KeyValueProperty(PropertyNode propertyNode, String key, String value) {
        if (propertyNode == null)
            throw new IllegalArgumentException("property is belongs to a property node.");
        if (StringUtils.isEmpty(key))
            throw new IllegalArgumentException("key could not be null.");

        this.propertyNode = propertyNode;
        this.key = key;
        this.value = value;
    }

    @Override
    public PropertyNode getNode() {
        return propertyNode;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public Object value() {
        return value;
    }

}
