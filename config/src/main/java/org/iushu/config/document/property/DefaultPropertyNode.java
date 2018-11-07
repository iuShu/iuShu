package org.iushu.config.document.property;

import java.util.Map;

/**
 * Created by iuShu on 18-11-6
 */
public class DefaultPropertyNode implements HierarchicalPropertyNode {

    private String key;
    private Property property;

    private Object value;

    private PropertyNode parent;
    private Map<String, PropertyNode> childes;

    public DefaultPropertyNode(String key) {
        this(key, null, null, null);
    }

    public DefaultPropertyNode(String key, Property property, Object value) {
        this(key, property, value, null, null);
    }

    public DefaultPropertyNode(String key, Property property, PropertyNode parent, Map<String, PropertyNode> childes) {
        this(key, property, null,  parent, childes);
    }

    public DefaultPropertyNode(String key, Property property, Object value, PropertyNode parent, Map<String, PropertyNode> childes) {
        this.key = key;
        this.property = property;
        this.value = value;
        this.parent = parent;
        this.childes = childes;
    }

    @Override
    public PropertyNode parent() {
        return parent;
    }

    @Override
    public Map<String, PropertyNode> child() {
        return childes;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Property getProperty() {
        return property;
    }

    @Override
    public Object getValue(Tokenizer key) {
        if (childes != null) {
            PropertyNode propertyNode =  childes.get(key.next());
            if (propertyNode != null)
                return propertyNode.getValue(key);
            return null;
        }

        // if no child
        if (this.key.equalsIgnoreCase(key.next()))
            return value;
        return null;
    }
}
