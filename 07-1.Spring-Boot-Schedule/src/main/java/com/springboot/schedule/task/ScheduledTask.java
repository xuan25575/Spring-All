package com.springboot.schedule.task;

import java.util.concurrent.ScheduledFuture;

public final class ScheduledTask {

    volatile ScheduledFuture<?> future;


    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
