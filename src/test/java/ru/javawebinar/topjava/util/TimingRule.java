package ru.javawebinar.topjava.util;

import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.HashMap;
import java.util.Map;

public class TimingRule extends Stopwatch{
    public static Map<String, Map<String, Long>> durations = new HashMap<>();

    @Override
    protected void finished(long nanos, Description description) {
        super.finished(nanos, description);
        if(!durations.containsKey(description.getClassName())){
            durations.put(description.getClassName(), new HashMap<>());
        }
        if(description.getMethodName() == null){
            System.out.println("===================================");
            System.out.println("Report");
            System.out.println("===================================");
            durations.get(description.getClassName()).forEach((key, value) -> System.out.printf("%-20s: %.2f millis\n", key, (double)value / 1000000.0));
            System.out.println("===================================");
        } else {
            durations.get(description.getClassName()).put(description.getMethodName(), nanos);
            System.out.println("Duration: " + nanos);
        }
    }
}
