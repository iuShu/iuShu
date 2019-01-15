package org.iushu.metrics;

import org.iushu.metrics.config.MetricsConfiguration;
import org.iushu.metrics.core.MetricsContext;

/**
 * Created by iuShu on 19-1-14
 */
public class Metrics {

    public static void main(String[] args) {

//        Metrics.startup();
        MetricsConfiguration configuration = new MetricsConfiguration();
        MetricsContext context = new MetricsContext(configuration);
    }

}
