package org.iushu.config.document.property;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by iuShu on 18-11-6
 */
public class DocumentPropertyRepository implements PropertyRepository {

    private String name;
    private Map<String, PropertyNode> nodeMap = Maps.newHashMap();

    public DocumentPropertyRepository(String name) {
        if (StringUtils.isEmpty(name))
            throw new IllegalArgumentException("property repository requires a name");
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<String> keys() { // TODO
        return null;
    }

    @Override
    public PropertyNode getRootPropertyNode(String key) {
        return nodeMap.get(key);
    }

    @Override
    public Object getValue(Tokenizer key) {
        PropertyNode propertyNode = nodeMap.get(key.next());
        if (propertyNode == null)
            return null;
        return propertyNode.getValue(key);
    }

    @Override
    public void put(PropertyNode propertyNode) {
        if (propertyNode == null)
            throw new IllegalArgumentException("PropertyNode could not be null in repository");

        nodeMap.put(propertyNode.getKey(), propertyNode);
    }

    @Override
    public HierarchicalPropertyNode matchingDepth(Tokenizer key) {
        HierarchicalPropertyNode node = (HierarchicalPropertyNode) nodeMap.get(key.next());
        if (node == null)
            return null;

        HierarchicalPropertyNode prev = node;
        while (!key.isEnd()) {
            HierarchicalPropertyNode child = (HierarchicalPropertyNode) prev.getChild(key.next());
            if (child == null)
                break;
            prev = child;
        }
        return prev;
    }

}
