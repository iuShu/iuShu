package org.iushu.config.document.property;

import java.util.Map;

/**
 * Created by iuShu on 18-11-5
 */
public interface HierarchicalPropertyNode extends PropertyNode {

    /**
     * @return the parent of this node
     */
    PropertyNode parent();

    /**
     * @return the child of this node, could be one or more.
     */
    Map<String, PropertyNode> child();

    void addParent(HierarchicalPropertyNode parent);

    void addChild(HierarchicalPropertyNode child);

}
