package org.iushu.config.document.property;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * MultiplePropertyNode was a special PropertyNode which contains multiple nodes with the same key,
 * and this node should be regarding as a small intermediate repository.
 *
 * As a special PropertyNode, MultiplePropertyNode do not have Value and Property, so that it's
 * un-support to setValue() and getProperty().
 *
 * getValue() was responsible for getting 'PropertyNode' value or 'PropertyNode Property' value of
 * PropertyNodes which stores in MultiplePropertyNode.
 *
 * Created by iuShu on 18-11-14
 */
public class MultiplePropertyNode implements PropertyNode {

    private String key;

    private Set<PropertyNode> nodes;

    public MultiplePropertyNode(String key, PropertyNode node) {
        if (StringUtils.isEmpty(key))
            throw new IllegalArgumentException("PropertyNode requires the key");

        this.key = key;
        this.nodes = Sets.newHashSet();
        addNode(node);
    }

    public String getKey() {
        return key;
    }

    /**
     * The tokenizer remain key would be this:
     *  1. PropertyKey=PropertyValue  --> getPropertyNodeValue()
     *  2. PropertyKey --> getPropertyValue()
     *
     * @return corresponding value
     */
    @Override
    public Object getValue(Tokenizer key) {
        String nodeKey = key.next();
        if (!nodeKey.contains(Tokenizer.SYMBOL_PROPERTY_MATCH))
            return getPropertyNodeValue(nodeKey);
        return getPropertyValue(nodeKey);
    }

    public void addNode(PropertyNode node) {
        Preconditions.checkNotNull(node, "It's not allow to add a NULL Node into PropertyNode.");
        Preconditions.checkArgument(!key.equals(node.getKey()), "The given PropertyNode should contains the same key as MultiplePropertyNode.");
        nodes.add(node);
    }

    public void setKey(String key) {
        Preconditions.checkArgument(StringUtils.isEmpty(key), "PropertyNode requires a not-null key.");
        this.key = key;
    }

    /**
     * PropertyNode Key
     *    |
     * <bean id="factory" class="...">param</bean>
     *                                  |
     *                        PropertyNode Value
     */
    private Object getPropertyNodeValue(String nodeKey) {
        String[] combine = nodeKey.split("\\" + Tokenizer.SYMBOL_PROPERTY_MATCH);
        for (PropertyNode node : nodes) {
            Property property = node.getProperty();
            if (property == null)
                continue;
            if (combine[0].equals(property.key()) && combine[1].equals(property.value()))
                return node.getValue(new Tokenizer(node.getKey()));
        }
        return null;
    }

    /**
     *  Property Key   Property Key
     *        |              |
     * <bean id="factory" class="...">param</bean>
     *              |             |
     *     Property Value   Property Value
     */
    private Object getPropertyValue(String propertyKey) {
        for (PropertyNode node : nodes) {
            Property property = node.getProperty();
            if (property == null)
                continue;
            if (propertyKey.equals(property.key()))
                return property.value();
        }
        return null;
    }

    @Override
    public void setValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Property getProperty() {
        throw new UnsupportedOperationException();
    }

}
