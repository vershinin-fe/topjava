package ru.javawebinar.topjava.util;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TimingRule extends Stopwatch{
    private static final Logger log = LoggerFactory.getLogger(TimingRule.class);
    private final Map<String, Long> durations = new HashMap<>();

    @Override
    protected void finished(long nanos, Description description) {
        super.finished(nanos, description);
        if(description.getMethodName() == null){
            durations.forEach((key, value) -> log.info(String.format("%-20s: %.2f ms", key, (double)value / 1000000.0)));
        } else {
            durations.put(description.getMethodName(), nanos);
            log.info("Duration: {}", nanos);
        }
    }
}

/*
private static final Logger log = LoggerFactory.getLogger(TimingRule.class);
    private static Map<String, Map<String, Long>> durations = new HashMap<>();

    @Override
    protected void finished(long nanos, Description description) {
        super.finished(nanos, description);
        if(!durations.containsKey(description.getClassName())){
            durations.put(description.getClassName(), new HashMap<>());
        }
        if(description.getMethodName() == null){
            durations.get(description.getClassName())
                    .forEach((key, value) -> log.info(String.format("%-20s: %.2f ms", key, (double)value / 1000000.0)));
        } else {
            durations.get(description.getClassName()).put(description.getMethodName(), nanos);
            log.info("Duration: {}", nanos);
        }
    }
 */