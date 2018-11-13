package org.iushu.config.document.resolver;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.iushu.config.document.Document;
import org.iushu.config.document.property.DefaultPropertyNode;
import org.iushu.config.document.property.DocumentPropertyRepository;
import org.iushu.config.document.property.PropertyNode;
import org.iushu.config.document.property.PropertyRepository;

import java.io.InputStream;

/**
 * Created by iuShu on 18-11-9
 */
public class Dom4jXmlResolver implements Resolver {

    @Override
    public PropertyRepository resolve(Document document) throws Exception {
        InputStream is = document.open();
        try {
            SAXReader reader = new SAXReader();
            org.dom4j.Document xmlDoc = reader.read(is);
            int nodeCount = xmlDoc.nodeCount(); // includes comment node counts.
            Element root = xmlDoc.getRootElement();
            PropertyRepository repository = new DocumentPropertyRepository(document.getName());
//            PropertyNode propertyNode = new DefaultPropertyNode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        return null;
    }
}
