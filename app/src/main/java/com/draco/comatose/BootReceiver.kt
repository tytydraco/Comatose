package com.draco.comatose

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        /* Start the Enforcer */
        if (sharedPreferences.getBoolean("startOnBoot", false) &&
                intent!!.action == Intent.ACTION_BOOT_COMPLETED)
            EnforcerWorker.enqueue(context)
    }

}