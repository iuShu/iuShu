package org.iushu.config.resource;

/**
 * binding resource to the corresponding resource type.
 *
 * create by iushu on 18-10-31
 * @deprecated
 */
public interface ResourceBinder {

    /**
     * @param param binding param
     * @return resource type
     */
    int binding(Object param);

}
