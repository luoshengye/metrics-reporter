package com.tydic.metrics.es;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.tydic.metrics.RunnableReporter;
import org.elasticsearch.metrics.ElasticsearchReporter;

import java.io.IOException;
import java.util.List;

public class EsRunnableReporter implements RunnableReporter {
    private MetricRegistry registry;
    private ScheduledReporter reporter;
    private Elasticsearch es;
    EsRunnableReporter(MetricRegistry registry,Elasticsearch es){
        this.registry = registry;
        this.es = es;
    }

    public void close() throws IOException {
        reporter.close();
    }

    public void run() {
        ElasticsearchReporter.Builder builder = ElasticsearchReporter.forRegistry(registry);
        final List<String> hosts = es.getHosts();
        builder.hosts(hosts.toArray(new String[hosts.size()]));
        try {
            reporter = builder.build();
            reporter.start(es.getPeriod(), es.getUnit());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
