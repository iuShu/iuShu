package org.iushu.config.document.repository;

public interface Property {

    PropertyNode getNode();

    String key();

    Object value();

}
