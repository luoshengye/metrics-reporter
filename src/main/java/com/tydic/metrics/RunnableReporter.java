package com.tydic.metrics;

import com.codahale.metrics.Reporter;

import java.io.Closeable;

public interface RunnableReporter extends Runnable,Reporter, Closeable{
    String fileName = "metrics-monitor.yaml";
}
