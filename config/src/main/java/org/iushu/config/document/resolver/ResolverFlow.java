package org.iushu.config.document.resolver;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.iushu.config.document.property.*;

import java.util.List;

/**
 * A helper class for Resolver components.
 *
 * Created by iuShu on 18-11-6
 */
public class ResolverFlow {

    public static PropertyNode newPropertyNode(Tokenizer key, Property property, Object value) {
        if (key.isEnd())
            return new DefaultPropertyNode(key.current(), property, value);

        String tokenize = key.next();
        DefaultPropertyNode rootNode = new DefaultPropertyNode(tokenize);
        DefaultPropertyNode prev = rootNode;
        while (true) {
            tokenize = key.next();
            DefaultPropertyNode subNode = new DefaultPropertyNode(tokenize);
            prev.addChild(subNode);

            if (key.isEnd()) {
                subNode.setValue(value);
                break;
            }
            prev = subNode;
        }
        return rootNode;
    }

    public static Property newProperty(Attribute attribute) {
        return new KeyValueProperty(attribute.getName(), attribute.getData());
    }

    public static Property newMultipleProperty(List<Attribute> attributes) {
        MultipleProperty multipleProperty = new MultipleProperty();
        for (Attribute attribute : attributes)
            multipleProperty.addProperty(newProperty(attribute));
        return multipleProperty;
    }

    public static PropertyNode newXmlPlainNode(Element element) {
        return new DefaultPropertyNode(element.getName(), getElementAttribute(element), element.getData());
    }

    public static PropertyNode newXmlNode(Element element, HierarchicalPropertyNode parent) {
        HierarchicalPropertyNode node = new DefaultPropertyNode(element.getName(), getElementAttribute(element), element.getData(), parent, null);
        parent.addChild(node);
        return node;
    }

    public static Property getElementAttribute(Element element) {
        List<Attribute> attributes = element.attributes();
        if (attributes.isEmpty())
            return null;

        if (attributes.size() == 1)
            return ResolverFlow.newProperty(attributes.get(0));
        return ResolverFlow.newMultipleProperty(attributes);
    }

}
