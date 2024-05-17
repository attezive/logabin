package com.example.logabin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.logabin.R;
import com.google.android.material.tabs.TabLayout;

public class EducationFragment extends Fragment {
    public EducationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TabLayout tl = getActivity().findViewById(R.id.navigation);
        tl.selectTab(null);
        return inflater.inflate(R.layout.fragment_education, container, false);
    }
}