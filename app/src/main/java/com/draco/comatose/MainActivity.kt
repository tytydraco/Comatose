package com.draco.comatose

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    var toggle: Button? = null
    val device_idle_constants = "inactive_to=60000," +
            "sensing_to=0," +
            "locating_to=0," +
            "locating_accuracy=10000," +
            "motion_inactive_to=0," +
            "idle_after_inactive_to=0," +
            "idle_pending_to=120000," +
            "max_idle_pending_to=120000," +
            "idle_pending_factor=2," +
            "idle_to=1800000," +
            "max_idle_to=21600000," +
            "idle_factor=2," +
            "min_time_to_alarm=3600000," +
            "max_temp_app_whitelist_duration=300000," +
            "mms_temp_app_whitelist_duration=60000," +
            "sms_temp_app_whitelist_duration=20000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toggle = findViewById<View>(R.id.toggle) as Button
        permission_check()
        refreshTitle()
        toggle!!.setOnClickListener { toggle() }
    }

    fun toggle() {
        if (enabled()) {
            Settings.Global.putString(contentResolver, "device_idle_constants", null)
            //Toast.makeText(getApplicationContext(), "Disabled", Toast.LENGTH_SHORT).show();
            refreshTitle()
        } else {
            Settings.Global.putString(contentResolver, "device_idle_constants", device_idle_constants)
            //Toast.makeText(getApplicationContext(), "Enabled" + device_idle_constants, Toast.LENGTH_SHORT).show();
            refreshTitle()
        }
    }

    fun enabled(): Boolean {
        val currentDeviceIdleConstants = Settings.Global.getString(contentResolver, "device_idle_constants")
        //Log.d("CURRENT_STATUS", currentDeviceIdleConstants);
        return currentDeviceIdleConstants != null
    }

    fun refreshTitle() {
        val currentlyEnabled = enabled()
        title = resources.getString(R.string.app_name) + " : " + if (currentlyEnabled) "Enabled" else "Disabled"
        if (currentlyEnabled) {
            toggle!!.text = getString(R.string.disable)
            toggle!!.setBackgroundColor(resources.getColor(R.color.colorRed))
        } else {
            toggle!!.text = getString(R.string.enable)
            toggle!!.setBackgroundColor(resources.getColor(R.color.colorGreen))
        }
    }

    fun permission_check() {
        val permissionCheck = ContextCompat.checkSelfPermission(this@MainActivity,
                Manifest.permission.WRITE_SECURE_SETTINGS)
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            val error = AlertDialog.Builder(this@MainActivity)
            error.setTitle("Missing Permissions")
            error.setMessage("To allow this app to work, you must run an ADB command via your computer.\n\nadb shell pm grant " + application.packageName + " android.permission.WRITE_SECURE_SETTINGS")
            error.setPositiveButton("Ok") { dialogInterface, i -> permission_check() }
            error.setNegativeButton("Close") { dialogInterface, i -> System.exit(0) }
            error.setCancelable(false)
            error.show()
        }
    }
}