package org.iushu.config.document.property;

import java.util.Collection;
import java.util.Map;

/**
 * Created by iuShu on 18-11-6
 */
public class DocumentPropertyRepository implements PropertyRepository {

    private String name;
    private Map<String, PropertyNode> nodeMap;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<String> keys() {
        // TODO
        return null;
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
}
