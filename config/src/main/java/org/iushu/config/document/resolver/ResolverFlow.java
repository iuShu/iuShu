package org.iushu.config.document.resolver;

import org.iushu.config.document.property.DefaultPropertyNode;
import org.iushu.config.document.property.Property;
import org.iushu.config.document.property.PropertyNode;
import org.iushu.config.document.property.Tokenizer;

/**
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

}
