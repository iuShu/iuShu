package org.iushu.config.context.hotswap;

import org.iushu.config.document.Document;

import java.io.File;

/**
 * Created by iuShu on 18-11-20
 */
public class Watchable {

    private long lastModifiedTimestamp;
    private Document document;
    private File file;

    public Watchable(Document document) {
        this.document = document;
        file = new File(document.getResource().getUrl());
        lastModifiedTimestamp = file.lastModified();
    }

    public boolean inWatch() {
        return file.exists();
    }

    public boolean isChanged() {
        return this.lastModifiedTimestamp != file.lastModified();
    }

    public void recordChanged() {
        this.lastModifiedTimestamp = file.lastModified();
    }

    public long getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setLastModifiedTimestamp(long lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
