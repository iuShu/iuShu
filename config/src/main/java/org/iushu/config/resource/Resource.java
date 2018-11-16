package org.iushu.config.resource;

import org.iushu.config.document.Document;

import java.io.InputStream;

/**
 * Resource represents a Document resource.
 *
 * Created by iuShu on 18-11-2
 */
public interface Resource {

    /**
     * @return the url of resource
     */
    String getUrl();

    /**
     * @return the name of resource.
     */
    String getName();

    /**
     * Resource have the responsibility to confirm the resource type and the corresponding
     * document type, deliver it to Document after that.
     *
     * @return deliver the resources to Document
     */
    Document deliver();

    /**
     * Open the resource as InputStream, singleProps or multiple stream is possible.
     *
     * @return the InputStream of resource
     */
    InputStream open() throws Exception;

}
