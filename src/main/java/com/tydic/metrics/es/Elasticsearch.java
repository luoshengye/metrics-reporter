package com.tydic.metrics.es;

import com.tydic.metrics.ReportConfig;

import java.util.List;

class Elasticsearch extends ReportConfig {

    private List<String> hosts;

    List<String> getHosts() {
        return hosts;
    }

    void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }
}