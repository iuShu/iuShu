package org.iushu.config.document.property;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * key value range: 0 ~ [tokenizer.size()-1];
 *
 * Created by iuShu on 18-11-6
 */
public class Tokenizer {

    public static final String SYMBOL_NODE = ".";
    public static final String SYMBOL_PROPERTY = "#";
    public static final String SYMBOL_PROPERTY_MATCH = "=";

    private String key;
    private List<String> tokenizer;
    private int index;

    public static Tokenizer create(String key) {
        return new Tokenizer(key);
    }

    public Tokenizer(String key) {
        Preconditions.checkArgument(StringUtils.isEmpty(key), "Key could not be null");
        this.key = key;
        this.tokenizer = parse();
    }

    public String first() {
        return tokenizer.get(0);
    }

    public String last() {
        return tokenizer.get(tokenizer.size()-1);
    }

    public String current() {
        if (index == tokenizer.size())
            return last();
        return tokenizer.get(index);
    }

    /**
     * The method will moving next the key of tokenize
     * @return the key which corresponding to key, always return last key if reached tail.
     */
    public String next() {
        if (index >= tokenizer.size())
            return last();
        return tokenizer.get(index++);
    }

    /**
     * move back one step.
     * @return the key which corresponding to key, always return first key if reached head.
     */
    public String back() {
        if (index <= 0)
            return first();
        return tokenizer.get(--index);
    }

    /**
     * @return whether if reached the last of tokenize.
     */
    public boolean isEnd() {
        return index == tokenizer.size();
    }

    public String getKey() {
        return key;
    }

    private List<String> parse() {
        if (!key.contains(SYMBOL_NODE))
            return Lists.newArrayList(key);

        String[] nodeKeys = key.split("\\" + SYMBOL_NODE);
        String tail = nodeKeys[nodeKeys.length - 1];
        if (!tail.contains(SYMBOL_PROPERTY))
            return Lists.newArrayList(nodeKeys);

        String[] keys = tail.split("\\" + SYMBOL_PROPERTY);
        nodeKeys[nodeKeys.length - 1] = keys[0];
        List<String> list = Lists.newArrayList(nodeKeys);
        list.add(keys[1]);
        return list;
    }
}
