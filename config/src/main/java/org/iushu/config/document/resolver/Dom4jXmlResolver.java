package org.iushu.config.document.resolver;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.iushu.config.context.ConfigContext;
import org.iushu.config.document.Document;
import org.iushu.config.document.property.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import static org.iushu.config.document.resolver.ResolverFlow.*;

/**
 * Default xml resolver is provided by Dom4j.
 *
 * Created by iuShu on 18-11-9
 */
public class Dom4jXmlResolver implements Resolver {

    private static Logger logger = LoggerFactory.getLogger(ConfigContext.DEFAULT_LOGGER);

    @Override
    public PropertyRepository resolve(Document document) throws Exception {
        logger.debug("[resolver] begin: {}", document.getName());

        InputStream is = document.open();
        try {
            SAXReader reader = new SAXReader();
            org.dom4j.Document xmlDoc = reader.read(is);
            PropertyRepository repository = new DocumentPropertyRepository(document.getName());
            Element root = xmlDoc.getRootElement();
            PropertyNode rootNode = newXmlPlainNode(root);
            recursive(root, (HierarchicalPropertyNode) rootNode);
            repository.put(rootNode);

            logger.debug("[resolver]   end: {}", document.getName());
            return repository;
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            is.close();
        }
        return null;
    }

    private void recursive(Element element, HierarchicalPropertyNode parent) {
        if (reachedTail(element))
            return;

        List<Element> elements = element.elements();
        for (Element e : elements) {
            PropertyNode node = newXmlNode(e, parent);
            recursive(e, (HierarchicalPropertyNode) node);
        }
    }

    private boolean reachedTail(Element element) {
        return element.elements().isEmpty();
    }

}
