package org.iushu.config.document.repository;

/**
 * Created by iuShu on 18-11-5
 */
public interface HierarchyPropertyNode extends PropertyNode {

    PropertyNode parent();

    PropertyNode child();

}
