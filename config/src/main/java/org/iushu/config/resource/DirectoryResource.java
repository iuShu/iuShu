package org.iushu.config.resource;

import org.iushu.config.document.StandardDocument;

/**
 * Created by iuShu on 18-11-1
 */
public class DirectoryResource extends AbstractLocalResource {

    public DirectoryResource(String url) {
        super(url);
    }

    @Override
    public StandardDocument deliver() {
        return null;
    }
}
