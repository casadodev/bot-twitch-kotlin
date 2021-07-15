package com.casadodev.application.util

import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit


class Scheduler {
    private val scheduler = ScheduledThreadPoolExecutor(1)
    private val logger = LoggerFactory.getLogger(Scheduler::class.java)
    private var isCanceled = false

    fun schedule(runnable: Runnable, initialDelay: Long, period: Long, timeUnit: TimeUnit?): ScheduledFuture<*> {
        logger.info("Beginning scheduler for $runnable")
        val timerHandle = scheduler.scheduleAtFixedRate(runnable, initialDelay, period, timeUnit)
        scheduler.removeOnCancelPolicy = true
        scheduler.executeExistingDelayedTasksAfterShutdownPolicy = false

        val timerCancelCheckExecutor = Executors.newSingleThreadScheduledExecutor()
        timerCancelCheckExecutor.scheduleAtFixedRate(
            { if (isCanceled) timerHandle.cancel(true) },
            1,
            1,
            TimeUnit.SECONDS
        )
        return timerHandle
    }

    // This is the code I need to put in whatever class I use this.
    val timer = Runnable {}
    fun cancel() {
        isCanceled = true
    }
}