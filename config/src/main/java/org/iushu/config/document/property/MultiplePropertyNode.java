package org.iushu.config.document.property;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * MultiplePropertyNode was a special PropertyNode which contains multiple nodes with the same key,
 * and this node should be regarding as a small intermediate repository. The repository stores the
 * Node by order, the order is the configuration's order.
 *
 * As a special PropertyNode, MultiplePropertyNode do not have Value and Property, so that it's
 * un-support to setValue() and getProperty().
 *
 * getValue() was responsible for getting 'PropertyNode' value or 'PropertyNode Property' value of
 * PropertyNodes which stores in MultiplePropertyNode.
 *
 * >> getPropertyNodeValue
 * PropertyNode Key
 *    |
 * <bean id="factory" class="...">param</bean>
 *                                  |
 *                        PropertyNode Value
 *
 * >> getPropertyValue
 *  Property Key   Property Key
 *        |              |
 * <bean id="factory" class="...">param</bean>
 *              |             |
 *     Property Value   Property Value
 *
 * Created by iuShu on 18-11-14
 */
public class MultiplePropertyNode implements PropertyNode {

    private String key;

    private int order = 1;
    Map<Integer, PropertyNode> nodeMap;

    public MultiplePropertyNode(String key, PropertyNode node) {
        if (StringUtils.isEmpty(key))
            throw new IllegalArgumentException("PropertyNode requires the key");

        this.key = key;
        this.nodeMap = Maps.newHashMap();
        addNode(node);
    }

    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException();
    }

    /**
     * NodeOrder is essential to location the specific node in MultiplePropertyNode.
     *
     * @return corresponding value
     */
    @Override
    public Object getValue(Tokenizer key) {
        int order = Integer.valueOf(key.next());
        if (key.isEnd())
            return getPropertyNodeValue(key);

        PropertyNode propertyNode = nodeMap.get(order);
        if (propertyNode == null)
            return null;
        return propertyNode.getValue(key);
    }

    public void addNode(PropertyNode node) {
        Preconditions.checkNotNull(node, "It's not allow to add a NULL Node into PropertyNode.");
        Preconditions.checkArgument(key.equals(node.getKey()), "The given PropertyNode should contains the same key as MultiplePropertyNode.");
        nodeMap.put(order++, node);
    }

    public void setKey(String key) {
        Preconditions.checkArgument(StringUtils.isEmpty(key), "PropertyNode requires a not-null key.");
        this.key = key;
    }

    private Object getPropertyNodeValue(Tokenizer key) {
        int order = Integer.valueOf(key.last());
        Preconditions.checkArgument(order > 0, "Illegal argument order: " + order);
        PropertyNode propertyNode = nodeMap.get(order);
        if (propertyNode == null)
            return null;
        return propertyNode.getValue(key);
    }

    public Collection<PropertyNode> propertyNodes() {
        return nodeMap.values();
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
