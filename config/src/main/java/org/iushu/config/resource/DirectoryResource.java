package org.iushu.config.resource;

import org.iushu.config.document.StandardDocument;

import java.io.InputStream;

/**
 * Created by iuShu on 18-11-1
 * @deprecated
 */
public class DirectoryResource extends AbstractLocalResource {

    public DirectoryResource(String url) {
        super(url);
    }

    @Override
    public StandardDocument deliver() {
        return null;
    }

    @Override
    public InputStream open() throws Exception {
        return null;
    }
}
