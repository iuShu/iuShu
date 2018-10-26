package org.iushu.config.component.xml;

import java.util.List;

/**
 * <properties>
 *     <property name="foo">bar</property>
 * </properties>
 *
 * <environment>
 *      <param name="foo1">bar1</param>
 *      <param name="foo2">bar2</param>
 * </environment>
 *
 * about the node like above, as following:
 *      DomNode(properties)   -- parent
 *          DomNode(property) -- child
 *
 *      DomNode(environment)  -- parent
 *          DomNode(param)    -- child
 *
 * Created by iuShu on 18-10-25
 */
public interface DomNode {

    /**
     * @NotNull
     * @return the name of this dom node.
     */
    String getName();

    /**
     * @Nullable null if root node.
     * @return the parent of this dom node.
     */
    DomNode getParent();

    /**
     * @Nullable null if reached end.
     * @return the child dom node.
     */
    List<DomNode> getChild();

}
