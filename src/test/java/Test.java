import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.tydic.metrics.IntegrateReporter;

public class Test {
    public static void main(String[] arg) {
        MetricRegistry registry = new MetricRegistry();
        Meter meter = new Meter();
        meter.mark();
        meter.mark();
        registry.register("meter",meter);
        IntegrateReporter reporter = new IntegrateReporter();
        reporter.setMetricRegistry(registry);
        reporter.run();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
