package org.iushu.config.document.property;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
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
            temp.addAll(Arrays.asList(key.split(".")));
        else
            temp.add(key);

        this.key = key;
        this.tokenizer = temp;
    }

    public String next() {
        if (index >= tokenizer.size())
            return "";
        return tokenizer.get(index++);
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
}
