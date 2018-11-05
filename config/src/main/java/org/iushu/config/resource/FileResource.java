package org.iushu.config.resource;

import org.iushu.config.definition.PropDefinition;
import org.iushu.config.definition.XmlDefinition;
import org.iushu.config.document.StandardDocument;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Single file resource
 *
 * Created by iuShu on 18-11-1
 */
public class FileResource extends AbstractLocalResource {

    public FileResource(String url) {
        super(url);
    }

    @Override
    public StandardDocument deliver() {
        return new StandardDocument(getName(), this, document -> {
            if ("xml".equalsIgnoreCase(getSuffix()))
                return new XmlDefinition(getName(), document);
            if ("properties".equalsIgnoreCase(getSuffix()))
                return new PropDefinition(getName(), document);

            throw new RuntimeException("Unsupported file type: " + getSuffix());
        });
    }

    @Override
    public InputStream open() throws Exception {
        return new FileInputStream(getUrl());
    }

}
