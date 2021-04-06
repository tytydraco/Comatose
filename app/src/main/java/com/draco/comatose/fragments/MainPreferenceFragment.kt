package com.draco.comatose.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.draco.comatose.R
import com.draco.comatose.repositories.constants.SettingsConstants
import com.draco.comatose.repositories.profiles.DeviceIdleConfigProfiles
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.snackbar.Snackbar

class MainPreferenceFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            getString(R.string.pref_profile_key_default) -> applyProfile(DeviceIdleConfigProfiles.DEFAULT)
            getString(R.string.pref_profile_key_light) -> applyProfile(DeviceIdleConfigProfiles.LIGHT)
            getString(R.string.pref_profile_key_moderate) -> applyProfile(DeviceIdleConfigProfiles.MODERATE)
            getString(R.string.pref_profile_key_high) -> applyProfile(DeviceIdleConfigProfiles.HIGH)
            getString(R.string.pref_profile_key_extreme) -> applyProfile(DeviceIdleConfigProfiles.EXTREME)

            getString(R.string.pref_developer_key) -> openURL(getString(R.string.developer_url))
            getString(R.string.pref_source_key) -> openURL(getString(R.string.source_url))
            getString(R.string.pref_contact_key) -> openURL(getString(R.string.contact_url))
            getString(R.string.pref_licenses_key) -> {
                val intent = Intent(requireContext(), OssLicensesMenuActivity::class.java)
                startActivity(intent)
            }
            else -> return super.onPreferenceTreeClick(preference)
        }
        return true
    }

    private fun applyProfile(config: String?) {
        val contentResolver = requireContext().contentResolver
        Settings.Global.putString(
            contentResolver,
            SettingsConstants.DEVICE_IDLE_CONSTANTS,
            config
        )
    }

    /**
     * Open a URL for the user
     */
    private fun openURL(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            Snackbar.make(requireView(), getString(R.string.snackbar_intent_failed), Snackbar.LENGTH_SHORT).show()
        }
    }
}