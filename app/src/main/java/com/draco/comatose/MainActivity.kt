package com.draco.comatose

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.provider.Settings
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.google.android.material.checkbox.MaterialCheckBox
import kotlin.concurrent.fixedRateTimer


class MainActivity : AppCompatActivity() {
    private val intentReceiver = IntentReceiver()

    private val adbCommand = "pm grant ${BuildConfig.APPLICATION_ID} android.permission.WRITE_SECURE_SETTINGS"

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var enable: Button
    private lateinit var disable: Button
    private lateinit var about: TextView
    private lateinit var status: TextView
    private lateinit var startOnBoot: MaterialCheckBox
    private lateinit var startOnSleep: MaterialCheckBox

    private fun setupUI() {
        enable.setOnClickListener {
            /* We should see the status automatically update */
            EnforcerWorker.enqueue(this)

            if (enable.animation == null || enable.animation.hasEnded()) {
                val bubbleAnimation = AnimationUtils.loadAnimation(this, R.anim.bubble)
                enable.startAnimation(bubbleAnimation)
            }

            updateStatusText()
        }

        disable.setOnClickListener {
            Settings.Global.putString(contentResolver, "device_idle_constants", null)
            EnforcerWorker.cancel(this)

            if (disable.animation == null || disable.animation.hasEnded()) {
                val bubbleAnimation = AnimationUtils.loadAnimation(this, R.anim.bubble)
                disable.startAnimation(bubbleAnimation)
            }

            updateStatusText()
        }

        startOnBoot.isChecked = sharedPreferences.getBoolean("startOnBoot", false)
        startOnSleep.isChecked = sharedPreferences.getBoolean("startOnSleep", false)

        startOnBoot.setOnCheckedChangeListener { _, checked ->
            editor.putBoolean("startOnBoot", checked)
            editor.apply()
        }

        startOnSleep.setOnCheckedChangeListener { _, checked ->
            editor.putBoolean("startOnSleep", checked)
            editor.apply()
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

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        editor = sharedPreferences.edit()

        enable = findViewById(R.id.enable)
        disable = findViewById(R.id.disable)
        about = findViewById(R.id.about)
        status = findViewById(R.id.status)
        startOnBoot = findViewById(R.id.start_on_boot)
        startOnSleep = findViewById(R.id.start_on_sleep)

        checkPermissions()

        /* Register screen off receiver */
        val screenStateFilter = IntentFilter()
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(intentReceiver, screenStateFilter)

        setupUI()

        /* Every second, update the status text */
        fixedRateTimer("statusCheck", false, 0, 1000) {
            updateStatusText()
        }

        /* Dark mode follows system theme */
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    /* If the constants in secure settings match our settings */
    private fun enabled(): Boolean {
        val currentConstants = Settings.Global.getString(contentResolver, "device_idle_constants")
        return currentConstants == idleConstants
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