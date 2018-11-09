package org.iushu.config.resource;

import java.io.File;

/**
 * AbstractResource represents local resources which base in file system.
 *
 * Created by iuShu on 18-11-1
 */
public abstract class AbstractLocalResource extends AbstractResource {

    /** generally is directory name or file name without suffix */
    private String name;

    /** only exist in file resource */
    private String suffix;

    public AbstractLocalResource(String url) {
        super(url);

        String fileName = splitPrefixDir();

        String[] nameInfo = fileName.split("\\.");
        if (nameInfo != null) { // file resource
            name = nameInfo[0];
            suffix = nameInfo[1];
        }
        else // directory resource
            name = fileName;
    }

    public String getName() {
        return name;
    }

    public String getSuffix() {
        return suffix;
    }

    /**
     * Split the parent directory if exists in given url.
     * <p>
     *     e.g.
     *      "home/demo/file.txt" will be return "file.txt"
     *      "home/demo" will be return "demo"
     *      "file.txt" will be return "file.txt"
     *      "demo" will be return "demo".
     * </p>
     *
     * @return file name or directory name without parent directory.
     */
    private String splitPrefixDir() {
        int separatorIndex = getUrl().lastIndexOf(File.separator);
        if (separatorIndex > 1)
            return getUrl().substring(separatorIndex + 1);
        return getUrl();
    }

}
