package org.iushu.config.resource;

import org.apache.commons.lang3.StringUtils;

/**
 * The abstract resource implementation, all resource entity would be the subclass of this.
 *
 * Created by iuShu on 18-10-31
 */
public abstract class AbstractResource implements Resource {

    private String url;

    public AbstractResource(String url) {
        if (StringUtils.isEmpty(url))
            throw new IllegalArgumentException("url requires NOT NULL");
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
