package org.iushu.config.document.property;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * Created by iuShu on 18-11-6
 */
public class MultipleProperty implements Property {

    private PropertyNode propertyNode;
    private Map<String, Property> propertyMap;

    public MultipleProperty(PropertyNode propertyNode, Property... propertyArray) {
        if (propertyNode == null)
            throw new IllegalArgumentException("Property is belongs to a PropertyNode.");

        Map<String, Property> temp = Maps.newHashMap();
        for (Property property : propertyArray)
            temp.put(property.key(), property);

        this.propertyNode = propertyNode;
        this.propertyMap = Collections.unmodifiableMap(temp);
    }

    @Override
    public PropertyNode getNode() {
        return propertyNode;
    }

    @Override
    public String key() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object value() {
        throw new UnsupportedOperationException();
    }

    public Object value(String key) {
        Property property = propertyMap.get(key);
        if (property != null)
            return property.value();
        return null;
    }

}
