package com.draco.comatose

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager

class IntentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        /* Boot */
        if (sharedPreferences.getBoolean("startOnBoot", false) &&
                intent!!.action == Intent.ACTION_BOOT_COMPLETED)
            EnforcerWorker.enqueue(context)

        /* Screen Off */
        if (intent!!.action == Intent.ACTION_SCREEN_OFF)
            EnforcerWorker.enqueue(context)
    }

}