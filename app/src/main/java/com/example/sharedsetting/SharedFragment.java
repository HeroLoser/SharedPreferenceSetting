package com.example.sharedsetting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

public class SharedFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private String Default = "Kosong";
    private String Name;
    private String Email;
    private String Age;
    private String Phone;
    private String Love;

    private EditTextPreference namePref;
    private EditTextPreference emailPref;
    private EditTextPreference agePref;
    private EditTextPreference phonePref;
    private CheckBoxPreference likePref;


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference);
        init();
        setSummaries();
    }

    private void init() {
        Name = getResources().getString(R.string.key_name);
        Email = getResources().getString(R.string.key_email);
        Age = getResources().getString(R.string.key_age);
        Phone = getResources().getString(R.string.key_phone);
        Love = getResources().getString(R.string.key_love);

        namePref = (EditTextPreference) findPreference(Name);
        emailPref = (EditTextPreference) findPreference(Email);
        agePref = (EditTextPreference) findPreference(Age);
        phonePref = (EditTextPreference) findPreference(Phone);
        likePref = (CheckBoxPreference) findPreference(Love);

    }

    private void setSummaries() {
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        namePref.setSummary(sharedPreferences.getString(Name, Default));
        emailPref.setSummary(sharedPreferences.getString(Email, Default));
        agePref.setSummary(sharedPreferences.getString(Age, Default));
        phonePref.setSummary(sharedPreferences.getString(Phone, Default));
        likePref.setChecked(sharedPreferences.getBoolean(Love, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(Name)){
            namePref.setSummary(sharedPreferences.getString(Name, Default));
        }
        if (s.equals(Email)){
            emailPref.setSummary(sharedPreferences.getString(Email, Default));
        }
        if (s.equals(Age)){
            agePref.setSummary(sharedPreferences.getString(Age, Default));
        }
        if (s.equals(Phone)){
            phonePref.setSummary(sharedPreferences.getString(Phone, Default));
        }
        if (s.equals(Love)){
            likePref.setChecked(sharedPreferences.getBoolean(Love, false));
        }

    }
}
