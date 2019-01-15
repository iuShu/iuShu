package org.iushu.metrics.utils;

import com.google.common.base.Preconditions;
import org.iushu.config.document.property.*;
import org.iushu.metrics.config.Flow;
import org.iushu.metrics.config.Group;
import org.iushu.metrics.config.Member;

import java.util.Map;

import static org.iushu.metrics.MetricsConstants.*;

/**
 * Created by iuShu on 19-1-15
 */
public class ConfigUtils {

    public static void setGroupProperty(Group group, Property property) {
        Preconditions.checkArgument(property instanceof MultipleProperty);

        MultipleProperty multipleProperty = (MultipleProperty) property;
        Object attrName = multipleProperty.getValue(XML_ATTRIBUTE_NAME);
        Object className = multipleProperty.getValue(XML_ATTRIBUTE_CLASS);

        Preconditions.checkArgument(attrName != null, "A group need to set a name.");
        Preconditions.checkArgument(className != null, "A group is requires specified the class.");

        group.setGroupName(attrName.toString());
        group.setClazzName(className.toString());
    }

    public static void setGroupMember(Group group, PropertyNode propertyNode) {
        HierarchicalPropertyNode node = (HierarchicalPropertyNode) propertyNode;
        Map<String, PropertyNode> members = node.childes();
        if (members == null || members.isEmpty())
            return;
        Preconditions.checkArgument(members.size() == 1, "Unknown configuration node under group");

        MultiplePropertyNode multipleMemberNodes = (MultiplePropertyNode) members.get(XML_NODE_MEMBER);
        for (PropertyNode memberNode : multipleMemberNodes.propertyNodes())
            newMember(group, memberNode);
    }

    public static Member newMember(Group group, PropertyNode propertyNode) {
        Property property = propertyNode.getProperty();

        Object methodName = propertyNode.getValue();
        Preconditions.checkArgument(methodName != null);

        Object memberName = methodName;
        if (property != null)
            memberName = property.value();

        Member member = new Member();
        member.setGroup(group);
        member.setMemberName(memberName.toString());
        member.setMethodName(methodName.toString());
        group.addMember(member);
        return member;
    }

    public static void setFlowProperty(Flow flow, Property property) {
        Object flowName = property.value();
        Preconditions.checkArgument(flowName != null);
    }

}
