package org.iushu.config.resource;

import com.google.common.collect.Maps;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Objects;

/**
 * Util of AbstractResource
 *
 * Created by iuShu on 18-10-31
 */
public class Resources {

    public static final int RESOURCE_UNKNOWN = -1;
    public static final int RESOURCE_FILE = 1;
    public static final int RESOURCE_DIR = 2;

    private static Map<Integer, Class<? extends Resource>> documentResources = Maps.newHashMap();

    // default resources corresponding to the class of documents.
    static {
        documentResources.put(RESOURCE_UNKNOWN, null);
        documentResources.put(RESOURCE_FILE, FileResource.class);
        documentResources.put(RESOURCE_DIR, DirectoryResource.class);
    }

    /**
     * for custom usage
     *
     * @param resourceType the resource type
     * @param resourceClass the corresponding document class
     */
    public static void registerDocumentType(int resourceType, Class<? extends AbstractResource> resourceClass) {
        checkResourceType(resourceType);
        if (resourceClass == null)
            throw new NullPointerException();

        documentResources.put(resourceType, resourceClass);
    }

    /**
     * @param relativePath the path relative of current project.
     * @return AbstractResource the corresponding resource of the given relative path.
     */
    public static Resource registerFile(String relativePath) {
        return register(relativePath, param -> {
            if (param == null)
                return RESOURCE_UNKNOWN;

            String path = (String) param;
            if (path.endsWith("xml") || path.endsWith("properties"))
                return RESOURCE_FILE;
            return RESOURCE_DIR;
        });
    }

    public static Resource register(Object param, ResourceBinder binder) {
        if (Objects.isNull(param))
            throw new NullPointerException();

        return newResource(binder.binding(param), param);
    }

    private static Resource newResource(int registerType, Object param) {
        Class<?> resourceClass = documentResources.get(registerType);
        if (resourceClass == null)
            return null;

        try {
            Constructor<Resource> constructor = (Constructor<Resource>) resourceClass.getConstructor(String.class);
            return constructor.newInstance(param);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void checkResourceType(int resourceType) {
        if (resourceType <= RESOURCE_DIR)
            throw new IllegalArgumentException("the resource type should greater than 2");
    }
}
