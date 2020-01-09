package com.draco.comatose

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {
    private val adbCommand = "pm grant ${BuildConfig.APPLICATION_ID} android.permission.WRITE_SECURE_SETTINGS"

    private lateinit var enable: Button
    private lateinit var disable: Button
    private lateinit var about: TextView
    private lateinit var status: TextView

    private val constants =
        "inactive_to=2592000000," +
        "motion_inactive_to=2592000000," +
        "light_after_inactive_to=15000," +
        "light_pre_idle_to=30000," +
        "light_max_idle_to=86400000," +
        "light_idle_to=43200000," +
        "light_idle_maintenance_max_budget=30000," +
        "light_idle_maintenance_min_budget=10000," +
        "min_time_to_alarm=60000"

    private fun setupUI() {
        enable.setOnClickListener {
            Settings.Global.putString(contentResolver, "device_idle_constants", constants)

            if (enable.animation == null || enable.animation.hasEnded()) {
                val bubbleAnimation = AnimationUtils.loadAnimation(this, R.anim.bubble)
                enable.startAnimation(bubbleAnimation)
            }

            updateStatusText()
        }

        disable.setOnClickListener {
            Settings.Global.putString(contentResolver, "device_idle_constants", null)

            if (disable.animation == null || disable.animation.hasEnded()) {
                val bubbleAnimation = AnimationUtils.loadAnimation(this, R.anim.bubble)
                disable.startAnimation(bubbleAnimation)
            }

            updateStatusText()
        }
    }

    private fun updateStatusText() {
        val enabledStatus = enabled()

        status.text = when (enabledStatus) {
            true -> "Comatose is enabled and working!"
            false -> "Comatose is current disabled."
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enable = findViewById(R.id.enable)
        disable = findViewById(R.id.disable)
        about = findViewById(R.id.about)
        status = findViewById(R.id.status)

        checkPermissions()

        setupUI()

        /* Every 10 seconds, update the status text */
        fixedRateTimer("statusCheck", false, 0, 10000) {
            updateStatusText()
        }
    }

    /* If the constants in secure settings match our settings */
    private fun enabled(): Boolean {
        val currentConstants = Settings.Global.getString(contentResolver, "device_idle_constants")
        return currentConstants == constants
    }

    private fun hasPermissions(): Boolean {
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SECURE_SETTINGS)
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermissions() {
        if (hasPermissions())
            return

        val dialog = AlertDialog.Builder(this)
                .setTitle("Missing Permissions")
                .setMessage(getString(R.string.adb_tutorial) + "adb shell $adbCommand")
                .setPositiveButton("Check Again", null)
                .setNeutralButton("Setup ADB", null)
                .setCancelable(false)
                .create()

        dialog.setOnShowListener {
            /* We don't dismiss on Check Again unless we actually have the permission */
            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                if (hasPermissions())
                    dialog.dismiss()
            }

            /* Open tutorial but do not dismiss until user presses Check Again */
            val neutralButton = dialog.getButton(AlertDialog.BUTTON_NEUTRAL)
            neutralButton.setOnClickListener {
                val uri = Uri.parse("https://www.xda-developers.com/install-adb-windows-macos-linux/")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }

        dialog.show()

        /* Check every second if the permission was granted */
        fixedRateTimer("permissionCheck", false, 0, 1000) {
            if (hasPermissions()) {
                dialog.dismiss()
                this.cancel()
            }
        }
    }
}