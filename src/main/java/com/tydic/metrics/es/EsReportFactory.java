package com.tydic.metrics.es;

import com.codahale.metrics.MetricRegistry;

import com.tydic.metrics.Configuration;
import com.tydic.metrics.ReportFactory;
import com.tydic.metrics.RunnableReporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class EsReportFactory implements ReportFactory {

    public RunnableReporter build(MetricRegistry registry) {
        Map esMap = Configuration.get("elasticsearch");
        if (esMap != null) {
            final Elasticsearch elasticsearch = setEs(esMap);
            return new EsRunnableReporter(registry,elasticsearch);
        }
        return null;
    }

    private Elasticsearch setEs(Map map) {
        final Elasticsearch es = new Elasticsearch();
        if (map.get("period") == null && map.get("timeunit") == null) {
            Configuration.setInterval(es, Configuration.getDefault());
        } else {
            Configuration.setInterval(es, map);
        }
        if (map.get("hosts") != null) {
            List<Map> list = (List<Map>) map.get("hosts");
            List<String> l = new ArrayList<>();
            for (Map host : list) {
                String str = host.get("host") + ":" + host.get("port");
                l.add(str);
            }
            es.setHosts(l);
        }
        return es;
    }

}
