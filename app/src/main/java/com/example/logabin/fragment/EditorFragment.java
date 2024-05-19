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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.logabin.R;
import com.example.logabin.adapter.EditMapAdapter;
import com.example.logabin.adapter.FoldersAdapter;
import com.example.logabin.adapter.NavigationAdapter;
import com.example.logabin.model.FolderItem;
import com.example.logabin.model.MapElementItem;

public class EditorFragment extends Fragment {
    private TextView infoMenu;
    private LinearLayout actionMenu;
    private MapElementItem currentItem;
    private int xSize;
    private int ySize;
    private int currentResize;
    private ImageView btnPositive;
    private ImageView btnNegative;
    RecyclerView editMapRV;

    public EditorFragment() {
        xSize = 3;
        ySize = 2;
        currentResize = 0;
        currentItem = null;
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
        actionMenu = root.findViewById(R.id.action_menu);

        btnPositive = root.findViewById(R.id.btn_positive);
        btnNegative = root.findViewById(R.id.btn_negative);
        ImageView btnStart = root.findViewById(R.id.btn_start);
        ImageView btnSave = root.findViewById(R.id.btn_save);
        ImageView btnResize = root.findViewById(R.id.btn_resize);

        editMapRV = root.findViewById(R.id.edit_map);


        editMapRV.setLayoutManager(new GridLayoutManager(getContext(), ySize,
                GridLayoutManager.HORIZONTAL, false));

        EditMapAdapter editMapAdapter = new EditMapAdapter(this, xSize, ySize);
        for (int i = 0; i < xSize*ySize; i++)
            editMapAdapter.Add(new MapElementItem(i));

        editMapRV.setAdapter(editMapAdapter);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnResize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentResize = (currentResize + 1) % 3;
                updateButtons();
            }
        });

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentResize == 0){
                    editMapAdapter.addNewYLine(1);
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) editMapRV.getLayoutManager();
                    gridLayoutManager.setSpanCount(editMapAdapter.getYSize());
                } else if (currentResize == 1) {
                    editMapAdapter.addNewXLine(1);
                    editMapAdapter.notifyDataSetChanged();
                } else {
                    //editMapAdapter.shiftMap();
                    editMapRV.offsetChildrenVertical(100);
                }
            }
        });

        editMapRV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_CANCEL ||
                    event.getAction() == MotionEvent.ACTION_UP){
                    editMapAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentResize == 0){
                    editMapAdapter.addNewYLine(-1);
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) editMapRV.getLayoutManager();
                    gridLayoutManager.setSpanCount(editMapAdapter.getYSize());
                } else if (currentResize == 1) {
                    editMapAdapter.addNewXLine(-1);
                } else {

                    editMapRV.offsetChildrenVertical(-100);
                }
            }
        });

        return root;
    }

    public void updateElement(MapElementItem item){
        if (currentItem == null || !currentItem.equals(item)){
            infoMenu.setText(item.toString());
            actionMenu.setVisibility(View.GONE);
            infoMenu.setVisibility(View.VISIBLE);

            currentItem = item;
        } else {
            infoMenu.setVisibility(View.GONE);
            actionMenu.setVisibility(View.VISIBLE);
            currentItem = null;
        }
    }

    private void updateButtons(){
        if (currentResize == 0){
            btnPositive.setImageResource(R.drawable.bottom);
            btnNegative.setImageResource(R.drawable.top);
        } else if (currentResize == 1) {
            btnPositive.setImageResource(R.drawable.left);
            btnNegative.setImageResource(R.drawable.right);
        } else {
            btnPositive.setImageResource(R.drawable.plus);
            btnNegative.setImageResource(R.drawable.minus);
        }
    }
}