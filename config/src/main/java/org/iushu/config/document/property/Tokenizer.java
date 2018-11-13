package org.iushu.config.document.property;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * index value range: 0 ~ [tokenizer.size()-1];
 *
 * Created by iuShu on 18-11-6
 */
public class Tokenizer {

    private String key;
    private List<String> tokenizer;
    private int index;
    private boolean isLast;

    public Tokenizer(String key) {
        if (StringUtils.isEmpty(key))
            throw new IllegalArgumentException("key could not be null");

        List<String> temp = Lists.newArrayList();
        if (key.contains("."))
            temp.addAll(Arrays.asList(key.split("\\.")));
        else
            temp.add(key);

        this.key = key;
        this.tokenizer = temp;
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
     * The method will moving next the index of tokenize
     * @return the key which corresponding to index, always return last key if reached tail.
     */
    public String next() {
        if (index >= tokenizer.size())
            return last();
        return tokenizer.get(index++);
    }

    /**
     * move back one step.
     * @return the key which corresponding to index, always return first key if reached head.
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

    public void reset() {
        index = 0;
    }

    public String getKey() {
        return key;
    }
}
