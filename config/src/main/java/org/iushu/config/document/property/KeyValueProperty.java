package org.iushu.config.document.property;

/**
 * Created by iuShu on 18-11-6
 */
public class KeyValueProperty implements Property {

    private PropertyNode propertyNode;
    private String key;
    private Object value;

    public KeyValueProperty(PropertyNode propertyNode, String key, Object value) {
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
