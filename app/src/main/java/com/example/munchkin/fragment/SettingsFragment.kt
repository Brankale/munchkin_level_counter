package com.example.munchkin.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.munchkin.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

}