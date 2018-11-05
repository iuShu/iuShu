package org.iushu.config.definition;

import org.iushu.config.document.Document;

/**
 * Created by iuShu on 18-11-2
 */
public abstract class AbstractDefinition implements Definition {

    private String name;
    private Document document;

    public AbstractDefinition(String name, Document document) {
        this.name = name;
        this.document = document;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Document getDocument() {
        return document;
    }

}
