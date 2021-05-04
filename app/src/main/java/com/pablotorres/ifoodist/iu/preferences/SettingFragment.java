package com.pablotorres.ifoodist.iu.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.pablotorres.ifoodist.R;

public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void onResume() {
        super.onResume();
        //Se registra el listener en el PreferenceManager
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Eliminamos el registro del listener en PreferenceManager
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.setting_preferences);

        //Se quiere recoger el evento OnSharedPreferenceChanged cuando la preferencia lista cambie
        onSharedPreferenceChanged(PreferenceManager.getDefaultSharedPreferences(getContext()), getString(R.string.key_ringtone_notification));
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if(key.equals(getString(R.string.key_ringtone_notification))){
            //La preferencia uqe ha lanzado el evento es la lista, por tanto, podemos hacer un downcasting
            ListPreference listPreference = (ListPreference)preference;
            //Vamos a obtener el indice del valor seleccionado en shared
            int index = listPreference.findIndexOfValue(sharedPreferences.getString(key,""));
            //Vemos si el valor es mayor a cero y por tanto existe un valor y se modifica el summary
            if(index >= 0)
                preference.setSummary(listPreference.getEntries()[index]);
            else
                preference.setSummary(sharedPreferences.getString(key,""));
        }
    }
}
