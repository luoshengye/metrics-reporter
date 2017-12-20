package com.tydic.metrics;

import java.util.concurrent.TimeUnit;

public class ReportConfig {
    private TimeUnit unit;
    private Long period;

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }
}
