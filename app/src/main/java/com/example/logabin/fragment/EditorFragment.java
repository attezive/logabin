package com.example.logabin.fragment;

import android.opengl.Visibility;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.logabin.R;
import com.example.logabin.adapter.EditMapAdapter;
import com.example.logabin.adapter.FoldersAdapter;
import com.example.logabin.adapter.NavigationAdapter;
import com.example.logabin.model.FolderItem;
import com.example.logabin.model.MapElementItem;

public class EditorFragment extends Fragment {
    private TextView infoMenu;
    private int currentElementId = -1;

    public EditorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editor, container, false);
        infoMenu = root.findViewById(R.id.info_menu);

        RecyclerView editMapRV = root.findViewById(R.id.edit_map);

        editMapRV.setLayoutManager(new GridLayoutManager(getContext(), 10,
                GridLayoutManager.HORIZONTAL, false));

        EditMapAdapter editMapAdapter = new EditMapAdapter(this);
        for (int i = 0; i < 200; i++)
            editMapAdapter.Add(new MapElementItem(i));

        editMapRV.setAdapter(editMapAdapter);

        return root;
    }

    public void updateElement(MapElementItem item){
        if (currentElementId!=item.getId()){
            infoMenu.setText(item.toString());
            infoMenu.setVisibility(View.VISIBLE);

            currentElementId = item.getId();
        } else {
            infoMenu.setVisibility(View.GONE);
            currentElementId = -1;
        }
    }
}