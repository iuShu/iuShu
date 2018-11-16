package org.iushu.config.document.property;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * Created by iuShu on 18-11-6
 */
public class MultipleProperty implements Property {

    private Map<String, Property> propertyMap;

    public MultipleProperty(Property... propertyArray) {
        Map<String, Property> temp = Maps.newHashMap();
        for (Property property : propertyArray)
            temp.put(property.key(), property);
        this.propertyMap = Maps.newHashMap(temp);
    }

    public void addProperty(Property... propertyArray) {
        for (Property property : propertyArray)
            this.propertyMap.put(property.key(), property);
    }

    @Override
    public String key() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object value() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isMultiple() {
        return true;
    }

    public Object getValue(String key) {
        Property property = propertyMap.get(key);
        if (property == null)
            return null;
        return property.value();
    }

}
