package org.iushu.config.document.property;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by iuShu on 18-11-6
 */
public class ResolverFlow {

    // adapt to the properties configuration
    public static PropertyNode newPropertyNode(Tokenizer key, Property property, Object value) {
        String tokenize = key.next();

        DefaultPropertyNode rootNode = null;
        if (StringUtils.isNotEmpty(tokenize))
            rootNode = new DefaultPropertyNode(tokenize);

        while (true) {
            tokenize = key.next();
            DefaultPropertyNode subNode = new DefaultPropertyNode(tokenize);
            rootNode.addChild(subNode);

            if (key.isEnd()) {
                subNode.setValue(value);
                break;
            }
        }

        return rootNode;
    }

}
