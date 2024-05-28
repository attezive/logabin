package com.example.logabin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.logabin.R;
import com.example.logabin.adapter.EducationAdapter;
import com.example.logabin.adapter.FoldersAdapter;
import com.example.logabin.db.model.EducationModel;
import com.example.logabin.model.EducationItem;
import com.example.logabin.model.FolderItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class EducationFragment extends Fragment {
    List<EducationModel> educationModelList;
    public EducationFragment(List<EducationModel> educationModelList) {
        this.educationModelList = educationModelList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_education, container, false);

        RecyclerView educationRV = root.findViewById(R.id.education_elements);
        educationRV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        EducationAdapter educationAdapter = new EducationAdapter();

        for (EducationModel educationModel : educationModelList)
            educationAdapter.Add(new EducationItem(educationModel));

        educationRV.setAdapter(educationAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        TabLayout tl = getActivity().findViewById(R.id.navigation);
        tl.selectTab(null);
    }
}