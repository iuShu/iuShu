package org.iushu.metrics.config;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.iushu.config.context.ConfigContext;
import org.iushu.config.context.GenericConfigContext;
import org.iushu.config.document.Document;
import org.iushu.config.document.property.HierarchicalPropertyNode;
import org.iushu.config.document.property.MultiplePropertyNode;
import org.iushu.config.document.property.PropertyNode;
import org.iushu.config.document.property.PropertyRepository;
import org.iushu.config.resource.scanner.ResourceScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static org.iushu.metrics.MetricsConstants.*;
import static org.iushu.metrics.core.MetricsContext.METRICS_LOGGER;
import static org.iushu.metrics.utils.ConfigUtils.*;

/**
 * Created by iuShu on 19-1-14
 */
public class MetricsConfiguration {

    private Document document;

    public MetricsConfiguration() {
        ResourceScanner scanner = ResourceScanner.defaultScanner();
        ConfigContext configContext = new GenericConfigContext(scanner);
        configContext.load();
        this.document = configContext.getDocument(DEFAULT_CONFIG_NAME);
    }

    public Map<String, Group> loadGroup() {
        PropertyRepository repository = document.getRepository();
        HierarchicalPropertyNode propertyNode = (HierarchicalPropertyNode) repository.getRootPropertyNode(XML_NODE_ROOT);
        Map<String, PropertyNode> metricsNodes = propertyNode.childes();
        PropertyNode groupNodes = metricsNodes.get(XML_NODE_GROUP);
        Preconditions.checkArgument(groupNodes != null);

        MultiplePropertyNode multipleNode = (MultiplePropertyNode) groupNodes;
        Map<String, Group> groups = Maps.newHashMap();
        for (PropertyNode node : multipleNode.propertyNodes()) {
            Group group = new Group();
            setGroupProperty(group, node.getProperty());
            setGroupMember(group, node);
            groups.put(group.getGroupName(), group);
        }

        return groups;
    }

    public void loadFlow(PropertyNode nodes) {
        Preconditions.checkArgument(nodes != null);

        MultiplePropertyNode flowNodes = (MultiplePropertyNode) nodes;
        List<Flow> flows = Lists.newArrayList();
        for (PropertyNode node : flowNodes.propertyNodes()) {
            Flow flow = new Flow();
            setFlowProperty(flow, node.getProperty());

            // TODO other property ...

            flows.add(flow);
        }
    }

}
