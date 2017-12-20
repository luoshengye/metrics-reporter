package com.tydic.metrics;

import com.codahale.metrics.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;


public class IntegrateReporter implements RunnableReporter {
    private MetricRegistry metricRegistry;
    private List<RunnableReporter> reporters = new ArrayList<>();

    public IntegrateReporter() {
        ServiceLoader<ReportFactory> serviceLoader = ServiceLoader.load(ReportFactory.class);
        for (ReportFactory aServiceLoader : serviceLoader) {
            final RunnableReporter reporter = aServiceLoader.build(metricRegistry);
            if (reporter != null) {
                reporters.add(reporter);
            }
        }
    }

    public void run() {
        if (metricRegistry == null) {
            throw new NullPointerException("MetricsRegistry is null!");
        }
        //run
        for (RunnableReporter reporter : reporters) {
            reporter.run();
        }
    }

    public void setMetricRegistry(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Override
    public void close() throws IOException {
        for (RunnableReporter reporter : reporters) {
            reporter.close();
        }
    }
}
