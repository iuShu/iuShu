package org.iushu.config.document;

import org.iushu.config.definition.DefineOperation;
import org.iushu.config.definition.Definition;
import org.iushu.config.resource.Resource;

import java.io.InputStream;

/**
 * Standard document, represents a single configuration file
 *
 * Created by iuShu on 18-10-31
 */
public class StandardDocument implements Document {

    private String name;
    private Resource resource;
    private DefineOperation defOpr;
    private Definition definition;

    public StandardDocument(String name, Resource resource, DefineOperation defOpr) {
        this.name = name;
        this.resource = resource;
        this.defOpr = defOpr;
        this.definition = defOpr.define(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Resource getResource() {
        return resource;
    }

    @Override
    public Definition getDefinition() {
        return definition;
    }

    @Override
    public InputStream open() throws Exception {
        return resource.open();
    }

}
