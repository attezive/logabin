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
import com.example.logabin.db.model.ElementModel;
import com.example.logabin.model.FolderItem;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    List<ElementModel> elementModelList;
    public MenuFragment(List<ElementModel> elementModelList) {
        this.elementModelList = elementModelList;
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

        foldersAdapter.Add(new FolderItem("Even", R.drawable.folder, elementModelList));
        foldersAdapter.Add(new FolderItem("Help", R.drawable.folder, getHelp()));
        foldersAdapter.Add(new FolderItem("Basis", R.drawable.folder, getBasis()));


        foldersRV.setAdapter(foldersAdapter);

        return root;
    }

    private List<ElementModel> getHelp(){
        List<ElementModel> elements = new ArrayList<>();
        for (ElementModel element: elementModelList) {
            if (element.name.equals("Wire") || element.name.equals("InputChannel") || element.name.equals("OutputChannel")){
                elements.add(element);
            }
        }
        return elements;
    }

    private List<ElementModel> getBasis(){
        List<ElementModel> elements = new ArrayList<>();
        for (ElementModel element: elementModelList) {
            if (element.name.equals("NotAnd") || element.name.equals("NotOr")){
                elements.add(element);
            }
        }
        return elements;
    }
}