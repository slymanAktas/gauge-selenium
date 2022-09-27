package uat.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Slf4j
public class ScheduledUtils {

    private ScheduledUtils() {
    }

    public static void threadWaiter(int milliSeconds) {
        Callable callableDelayedTask = () -> " ms waited...";
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1);

        ScheduledFuture sf = scheduledPool.schedule(callableDelayedTask, milliSeconds, MILLISECONDS);

        String value = null;
        try {
            value = (String) sf.get();
        } catch (InterruptedException | ExecutionException e) {
            log.trace(e.getMessage());
            Thread.currentThread().interrupt();
        }

        scheduledPool.shutdown();
        log.trace(milliSeconds + value);
    }
}