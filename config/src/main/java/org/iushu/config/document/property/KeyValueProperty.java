package org.iushu.config.document.property;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by iuShu on 18-11-6
 */
public class KeyValueProperty implements Property {

    private String key;
    private Object value;

    public KeyValueProperty(String key, Object value) {
        Preconditions.checkArgument(StringUtils.isEmpty(key), "Property requires a not-null key.");
        this.key = key;
        this.value = value;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public Object value() {
        return value;
    }

    @Override
    public boolean isMultiple() {
        return false;
    }
}
