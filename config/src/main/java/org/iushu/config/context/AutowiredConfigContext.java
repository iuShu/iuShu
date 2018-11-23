package org.iushu.config.context;

import com.google.common.collect.Maps;
import org.iushu.config.annotation.AutoConfig;
import org.iushu.config.annotation.AutoValue;
import org.iushu.config.document.Document;
import org.iushu.config.document.property.Tokenizer;
import org.iushu.config.resource.Resource;
import org.iushu.config.resource.scanner.ResourceScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * The config entity of configCache do not
 *
 * Created by iuShu on 18-11-20
 */
public class AutowiredConfigContext extends GenericConfigContext {

    private static Logger logger = LoggerFactory.getLogger(DEFAULT_LOGGER);

    private static final String ENTITY_FILE_SUFFIX = "class";
    private ResourceScanner entityScanner;
    private Map<String, Object> configCache;

    public AutowiredConfigContext(ResourceScanner configScanner, ResourceScanner entityScanner) {
        super(configScanner);
        entityScanner.suffix(ENTITY_FILE_SUFFIX);
        this.entityScanner = entityScanner;
        this.configCache = Maps.newConcurrentMap();
    }

    @Override
    public void load() {
        super.load();
        autowiredEntities();
    }

    /**
     * Scan the locations to get the entities and new entity instance which indicates the
     * unique reference of this configuration entity. The instance would not be change after
     * cached it.
     */
    private void autowiredEntities() {
        try {
            List<Resource> resources = entityScanner.scan();
            for (Resource resource : resources) {
                String resourcePath = ResourceScanner.getResourceJavaPath(resource);
                Class<?> entityClass = AutowiredConfigContext.class.getClassLoader().loadClass(resourcePath);
                AutoConfig autoConfig = entityClass.getAnnotation(AutoConfig.class);
                if (autoConfig == null)
                    continue;
                Object entity = entityClass.newInstance(); // the unique reference entity
                doAutowired(autoConfig.name(), entity);

                logger.debug("[context] autowired: " + entityClass.getCanonicalName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAutowired(String configName, Object entity) {
        Document document = getDocument(configName);
        if (document == null)
            return;

        try {
            Class<?> entityClass = entity.getClass();
            for (Field field : entityClass.getDeclaredFields()) {
                AutoValue autoValue = field.getAnnotation(AutoValue.class);
                if (autoValue == null)
                    continue;
                Object value = document.getRepository().getValue(new Tokenizer(autoValue.key()));
                setProperty(field.getName(), value, entity);
            }
            configCache.put(configName, entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setProperty(String fieldName, Object value, Object entity) throws IntrospectionException {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, entity.getClass());
            Method setMethod = propertyDescriptor.getWriteMethod();
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            PropertyEditor propertyEditor = PropertyEditorManager.findEditor(propertyType);
            propertyEditor.setAsText(value == null ? "" : value.toString());
            setMethod.invoke(entity, propertyEditor.getValue());
        } catch (IntrospectionException e) {
            throw new IntrospectionException(fieldName + " requires a setter method.");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T getEntity(String configName, Class<T> clazz) {
        Object entity = configCache.get(configName);
        if (entity.getClass() != clazz)
            return null;
        return (T) entity;
    }

    /**
     * In this phase, do not modify the reference of the entity which had already referenced by user programs.
     * That is, the configCache will be cache the unique reference of the entity forever once creates, no matter
     * how many times reloading the corresponding document.
     *
     * @param document the reloaded document
     * @throws Exception
     */
    @Override
    protected void reload(Document document) throws Exception {
        super.reload(document);
        Object entity = configCache.get(document.getName());
        if (entity == null)
            return;
        // autowiredEntities property in original entity after reload
        doAutowired(document.getName(), entity);
    }

    @Override
    protected void remove(Document document) {
        super.remove(document);
        configCache.remove(document.getName());
    }

    @Override
    public void close() throws IOException {
        super.close();
        configCache.clear();
    }

}
