package com.draco.comatose.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.preference.PreferenceManager
import com.draco.comatose.R
import com.draco.comatose.repositories.constants.SettingsConstants

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val config = sharedPrefs.getString(context.getString(R.string.pref_saved_constants_key), null) ?: return

            Settings.Global.putString(
                context.contentResolver,
                SettingsConstants.DEVICE_IDLE_CONSTANTS,
                config
            )
        }
    }
}