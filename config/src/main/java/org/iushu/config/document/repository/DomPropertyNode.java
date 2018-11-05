package org.iushu.config.document.repository;

/**
 * Dom Property Node commonly use in xml configuration.
 *
 * Created by iuShu on 18-11-5
 */
public class DomPropertyNode implements HierarchyPropertyNode {

    private String name;
    private Property property;

    private Object value;

    private PropertyNode parent;
    private PropertyNode child;

    public DomPropertyNode(String name, Property property, Object value) {
        this(name, property, value, null, null);
    }

    public DomPropertyNode(String name, Property property, PropertyNode parent, PropertyNode child) {
        this(name, property, null, parent, child);
    }

    public DomPropertyNode(String name, Property property, Object value, PropertyNode parent, PropertyNode child) {
        this.name = name;
        this.property = property;
        this.value = value;
        this.parent = parent;
        this.child = child;
    }

    @Override
    public PropertyNode parent() {
        return parent;
    }

    @Override
    public PropertyNode child() {
        return child;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Property getProperty() {
        return property;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
