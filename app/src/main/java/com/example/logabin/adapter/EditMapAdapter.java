package com.example.logabin.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logabin.R;
import com.example.logabin.databinding.MapElementItemBinding;
import com.example.logabin.fragment.EditorFragment;
import com.example.logabin.model.MapElementItem;

import java.util.ArrayList;
import java.util.List;


public class EditMapAdapter extends RecyclerView.Adapter<EditMapAdapter.EditMapViewHolder> {
    private List<MapElementItem> list = new ArrayList<>();
    private List<LinearLayout> map = new ArrayList<>();
    private int xSize;
    private int ySize;
    private EditorFragment editorFragment;

    public EditMapAdapter(EditorFragment fragment, int xSize, int ySize){
        super();
        editorFragment = fragment;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    @NonNull
    @Override
    public EditMapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newView = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_element_item,
                parent, false);
        return new EditMapViewHolder(newView);
    }

    public List<LinearLayout> getMap() {
        return map;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull EditMapViewHolder holder, int position) {
        holder.bind(list.get(position), position);

    }

    public class EditMapViewHolder extends RecyclerView.ViewHolder{
        private MapElementItemBinding mapElementItemBinding;
        public EditMapViewHolder(@NonNull View itemView) {
            super(itemView);
            mapElementItemBinding = MapElementItemBinding.bind(itemView);
        }

        public void bind(MapElementItem mapElementItem, int position){
            mapElementItemBinding.mapElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editorFragment.updateElement(mapElementItem);
                }
            });
            map.add(position, mapElementItemBinding.mapElement);
        }
    }

    public void Add(MapElementItem mapElementItem){
        list.add(mapElementItem);
        notifyItemInserted(list.size());
    }

    public void Add(MapElementItem mapElementItem, int position){
        list.add(position, mapElementItem);
        notifyItemInserted(position);
    }

    public void addNewXLine(int direction){
        if (direction == 1){
            for (int i = 0; i < ySize; i++){
                Add(new MapElementItem(ySize-i-1), 0);
            }
        } else {
            for (int i = 0; i < ySize; i++){
                Add(new MapElementItem(xSize*ySize+i));
            }
        }
        xSize++;
        updateIds();
    }

    public void addNewYLine(int direction){
        if (direction == 1){
            for (int i = 0; i < xSize; i++){
                Add(new MapElementItem(0), (i+1)*ySize+i);
            }
        } else {
            for (int i = 0; i < xSize; i++){
                Add(new MapElementItem(0), i*(ySize+1));
            }
        }
        ySize++;
        updateIds();
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public void updateIds() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(i);
        }
    }
}