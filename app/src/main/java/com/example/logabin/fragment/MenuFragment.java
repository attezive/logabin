package com.example.logabin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.logabin.R;
import com.example.logabin.adapter.FoldersAdapter;
import com.example.logabin.model.FolderItem;

public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        RecyclerView foldersRV = root.findViewById(R.id.folders);
        foldersRV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        FoldersAdapter foldersAdapter = new FoldersAdapter();
        foldersAdapter.Add(new FolderItem("tsad"));
        foldersAdapter.Add(new FolderItem("asf"));
        foldersAdapter.Add(new FolderItem("asf"));

        foldersRV.setAdapter(foldersAdapter);

        return root;
    }
}