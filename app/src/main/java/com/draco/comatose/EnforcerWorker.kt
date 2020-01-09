package com.draco.comatose

import android.content.Context
import android.provider.Settings
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class EnforcerWorker(context: Context, workerParams: WorkerParameters)
    : Worker(context, workerParams) {

    companion object {
        fun enqueue(context: Context) {
            /* Make sure we cancel existing work first */
            cancel(context)

            val work = PeriodicWorkRequestBuilder<EnforcerWorker>(
                    15,
                    TimeUnit.MINUTES
            ).build()
            WorkManager.getInstance(context).enqueue(work)
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelAllWork()
        }
    }

    override fun doWork(): Result {
        Settings.Global.putString(applicationContext.contentResolver, "device_idle_constants", idleConstants)

        return Result.success()
    }

}