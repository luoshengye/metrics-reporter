package com.tydic.metrics;

import com.google.common.io.Resources;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Configuration {
    private static Map map = parse(RunnableReporter.fileName);

    private static Map parse(String fileName) {
        try {
            //解析yaml
            String path = Resources.getResource(fileName).getFile();
            Yaml yaml = new Yaml();
            FileInputStream in = new FileInputStream(path);
            Object load = yaml.load(in);
            if (load instanceof Map) {
                return (Map) load;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map get(String type) {
        if (map != null) {
            return (Map) map.get(type);
        }
        return null;
    }

    public static Map getDefault() {
        return get("default");
    }

    public static void setInterval(ReportConfig mb, Map map) {
        mb.setPeriod(((Integer) map.get("period")).longValue());
        String unit = (String) map.get("timeunit");
        if ("SECONDS".equalsIgnoreCase(unit)) {
            mb.setUnit(TimeUnit.SECONDS);
        } else if ("MINUTES".equalsIgnoreCase(unit)) {
            mb.setUnit(TimeUnit.MINUTES);
        } else
            mb.setUnit(TimeUnit.HOURS);
    }
}
