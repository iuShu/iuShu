package org.iushu.config.resource.scanner;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.iushu.config.resource.FileResource;
import org.iushu.config.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * A standard configuration resource file should contains filename and file type(suffix),
 * and the scanner only able to recognize those files.
 *
 * The default setting of the scanner will scanning the file with file type is "xml" and "properties".
 * For more details, enjoy custom.
 *
 * Created by iuShu on 18-11-15
 */
public class ResourceScanner {

    private static Logger logger = LoggerFactory.getLogger("Configuration");

    public static final int INCLUDE_FILE_SUFFIX = 0x01;
    public static final int EXCLUDE_FILE_NAME = 0x04;

    public static final int EXCLUDE_DIR = 0x17;

    // default supported file types
    public static final String XML = "xml";
    public static final String PROP = "properties";

    private ScanFilter fileFilter;
    private ScanFilter dirFilter;

    private String root; // project root directory
    private Set<String> locations;

    private static final ResourceScanner INSTANCE = new ResourceScanner();

    private ResourceScanner() {
        root = ResourceScanner.class.getClassLoader().getResource("").getPath();
        locations = Sets.newHashSet();
        fileFilter = new FileScanFilter();
        dirFilter = new DirectoryScanFilter();

        // default setting
        suffix(XML, PROP);
    }

    public static ResourceScanner location(String... locations) {
        INSTANCE.locations.addAll(Arrays.asList(locations));
        return INSTANCE;
    }

    public ResourceScanner suffix(String... suffixes) {
        logger.debug("[scanner-suffix] include: {}", suffixes);
        return condition(INCLUDE_FILE_SUFFIX, suffixes);
    }

    public ResourceScanner excludeDir(String... directories) {
        logger.debug("[scanner-directory] exclude: {}", directories);
        return condition(EXCLUDE_DIR, directories);
    }

    public ResourceScanner excludeFileName(String... filenames) {
        logger.debug("[scanner-filename] exclude: {}", filenames);
        return condition(EXCLUDE_FILE_NAME, filenames);
    }

    public ResourceScanner condition(int type, String... conditions) {
        if (type >> 4 == 0)
            fileFilter.register(type, conditions);
        else
            dirFilter.register(type, conditions);
        return this;
    }

    public List<Resource> scan() {
        logger.debug("[scanner-scan] start");

        List<Resource> resources = Lists.newArrayList();
        for (String dir : locations) {
            File file = new File(root + dir);
            if (file.exists())
                recursiveScan(file, resources);
        }

        logger.debug("[scanner-scan] end, with resources: {}", resources.size());
        return resources;
    }

    private void recursiveScan(File file, List<Resource> resources) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile() && !filterFile(f))
                resources.add(new FileResource(f.getAbsolutePath()));
            else if (f.isDirectory() && !filterDirectory(f))
                recursiveScan(f, resources);
        }
    }

    private boolean filterFile(File file) {
        return fileFilter.exclude(file);
    }

    private boolean filterDirectory(File dir) {
        return dirFilter.exclude(dir);
    }

}
