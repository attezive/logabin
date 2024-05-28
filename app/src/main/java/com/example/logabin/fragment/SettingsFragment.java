package com.example.logabin.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.logabin.R;
import com.google.android.material.tabs.TabLayout;


public class SettingsFragment extends Fragment {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public SettingsFragment(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        editor = preferences.edit();

        CheckBox auto_interact = root.findViewById(R.id.auto_interact);

        if (preferences.getBoolean("AutoInteract", false)){
            auto_interact.setChecked(true);
        }
        EditorFragment.setAutoInteract(preferences.getBoolean("AutoInteract", false));

        auto_interact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (auto_interact.isChecked()!=preferences.getBoolean("AutoInteract", false)){
                    editor.putBoolean("AutoInteract", true);
                }
                editor.commit();
                EditorFragment.setAutoInteract(preferences.getBoolean("AutoInteract", false));
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        TabLayout tl = getActivity().findViewById(R.id.navigation);
        tl.selectTab(null);
    }
}