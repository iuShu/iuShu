package org.iushu.config.document.property;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
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
        this.childes = childes == null ? Maps.newHashMap() : Maps.newHashMap(childes);
    }

    @Override
    public PropertyNode parent() {
        return parent;
    }

    @Override
    public Map<String, PropertyNode> childes() {
        return Collections.unmodifiableMap(childes);
    }

    @Override
    public void addParent(HierarchicalPropertyNode parent) {
        Preconditions.checkNotNull(parent);
        this.parent = parent;
    }

    @Override
    public void addChild(HierarchicalPropertyNode child) {
        Preconditions.checkNotNull(child);

        PropertyNode node = childes.get(child.getKey());
        if (node == null) {
            childes.put(child.getKey(), child);
            return;
        }

        if (node instanceof MultiplePropertyNode)
            ((MultiplePropertyNode) node).addNode(child);
        else {
            // PropertyNode transform to MultiplePropertyNode
            MultiplePropertyNode newNode = new MultiplePropertyNode(child.getKey(), node);
            newNode.addNode(child);
            childes.put(child.getKey(), newNode);
        }
    }

    @Override
    public PropertyNode getChild(String key) {
        return childes.get(key);
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
    public Object getValue() {
        return value;
    }

    @Override
    public Object getValue(Tokenizer key) {
        String next = key.next();

        // current node property value
        if (next.contains(Tokenizer.SYMBOL_PROPERTY))
            return getPropertyValue(next);

        // contain childes and key is not end
        if (!childes.isEmpty()) {
            PropertyNode propertyNode = childes.get(next);
            if (propertyNode != null)
                return propertyNode.getValue(key);
        }

        // no childes or the key reached tail
        if (this.key.equalsIgnoreCase(next) || StringUtils.isNumeric(next))
            return value;
        return null;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    public Object getPropertyValue(String propertyKey) {
        String key = propertyKey.substring(1); // remove # property symbol
        if (property instanceof MultipleProperty)
            return ((MultipleProperty) property).getValue(key);
        if (key.equals(property.key()))
            return value;
        return null;
    }

}
