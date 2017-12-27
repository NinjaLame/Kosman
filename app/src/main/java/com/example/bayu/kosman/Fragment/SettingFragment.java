package com.example.bayu.kosman.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.bayu.kosman.R;
import com.example.bayu.kosman.Static;
import com.example.bayu.kosman.api.Owners;

public class SettingFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences,rootKey);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.i("Stat","onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences mysharedPref = getContext().getSharedPreferences(Static.MY_PREFS,Static.prefMode);
        SharedPreferences.Editor edit = mysharedPref.edit();
        edit.putString("name",sharedPref.getString("name","loading"));
        edit.putString("email",sharedPref.getString("email","loading"));
        edit.putString("phone",sharedPref.getString("phone","loading"));
        edit.commit();
        String userId = mysharedPref.getString("userId","loading");
        Owners own = new Owners(getContext());
        own.updateOwner(userId);
        Log.i("Stat","onDetach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
