package org.iushu.config.resource.scanner;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.iushu.config.resource.scanner.ResourceScanner.*;

/**
 * Created by iuShu on 18-11-15
 */
public class DirectoryScanFilter implements ScanFilter {

    private List<String> excludeDirs;

    public DirectoryScanFilter() {
        excludeDirs = Lists.newArrayList();
    }

    @Override
    public void register(int type, String... condition) {
        if (type == EXCLUDE_DIR)
            excludeDirs.addAll(Arrays.asList(condition));
        else
            throw new IllegalArgumentException("Unsupported operation type: " + type);
    }

    @Override
    public boolean exclude(File file) {
        if (excludeDirs.isEmpty())
            return false;
        if (excludeDirs.contains(file.getAbsolutePath()))
            return true;
        return false;
    }
}
