package org.iushu.config.resource.scanner;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.iushu.config.resource.scanner.ResourceScanner.*;

/**
 * Created by iuShu on 18-11-15
 */
public class FileScanFilter implements ScanFilter {

    private List<String> suffixes;
    private List<String> excludeFileNames;

    public FileScanFilter() {
        suffixes = Lists.newArrayList();
        excludeFileNames = Lists.newArrayList();
    }

    @Override
    public void register(int type, String... condition) {
        switch (type){
            case INCLUDE_FILE_SUFFIX:
                suffixes.addAll(Arrays.asList(condition));
                break;
            case EXCLUDE_FILE_NAME:
                excludeFileNames.addAll(Arrays.asList(condition));
                break;
            default:
                throw new IllegalArgumentException("Unsupported operation type: " + type);
        }
    }

    @Override
    public boolean exclude(File file) {
        String[] combine = file.getName().split("\\.");
        if (excludeFileNames.contains(combine[0]))
            return true;
        if (combine.length <= 1) // file without suffix
            return true;
        if (suffixes.contains(combine[1]))
            return false;
        return true;
    }

}
