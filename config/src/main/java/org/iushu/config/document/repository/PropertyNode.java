package org.iushu.config.document.repository;

/**
 * A node represents the key-value pair in properties,
 * also can be represents the tag with multiple property in xml.
 *
 * Created by iuShu on 18-11-5
 */
public interface PropertyNode {

    /**
     * Node which represents the key in properties and the tag name in xml.
     *
     * @return the node name
     */
    String getName();

    /**
     * @return the property of node
     */
    Property getProperty();

    /**
     * @return the value of this node
     */
    Object getValue();

}
