package org.iushu.metrics.core;

import org.iushu.metrics.config.Flow;
import org.iushu.metrics.config.Group;
import org.iushu.metrics.config.Member;
import org.iushu.metrics.config.MetricsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by iuShu on 19-1-14
 */
public class MetricsContext {

    public static final String METRICS_LOGGER = "Metrics";

    private Map<String, Group> groups;

    private Map<String, Flow> flows;

    public MetricsContext(MetricsConfiguration configuration) {
        groups = configuration.loadGroup();
//        flow = configuration.loadFlow();
    }

    public Group getGroup(String groupName) {
        return groups.get(groupName);
    }

    public Member getMember(String groupName, String memberName) {
        Group group = groups.get(groupName);
        return group == null ? null : group.getMember(memberName);
    }

}
