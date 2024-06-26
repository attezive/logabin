package com.example.logabin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.logabin.R;
import com.google.android.material.tabs.TabLayout;

public class SchemesFragment extends Fragment {

    public SchemesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schemes, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        TabLayout tl = getActivity().findViewById(R.id.navigation);
        tl.selectTab(null);
    }
}