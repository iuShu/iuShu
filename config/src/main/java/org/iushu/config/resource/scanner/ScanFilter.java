package org.iushu.config.resource.scanner;

import java.io.File;

/**
 * Created by iuShu on 18-11-15
 */
public interface ScanFilter {

    void register(int type, String... condition);

    /**
     * be aware of the separator of File url
     * @return true-exclude
     */
    boolean exclude(File file);

}
