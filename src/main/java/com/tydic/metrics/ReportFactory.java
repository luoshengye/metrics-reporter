package com.tydic.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.tydic.metrics.RunnableReporter;

public interface ReportFactory {
    RunnableReporter build(MetricRegistry registry);
}
