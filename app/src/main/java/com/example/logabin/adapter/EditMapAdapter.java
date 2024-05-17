package com.example.logabin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logabin.R;
import com.example.logabin.databinding.FolderItemBinding;
import com.example.logabin.databinding.MapElementItemBinding;
import com.example.logabin.fragment.EditorFragment;
import com.example.logabin.model.FolderItem;
import com.example.logabin.model.MapElementItem;

import java.util.ArrayList;
import java.util.List;


public class EditMapAdapter extends RecyclerView.Adapter<EditMapAdapter.EditMapViewHolder> {
    private List<MapElementItem> list = new ArrayList<>();
    EditorFragment editorFragment = null;

    public EditMapAdapter(EditorFragment fragment){
        super();
        editorFragment = fragment;
    }

    @NonNull
    @Override
    public EditMapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newView = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_element_item,
                parent, false);
        return new EditMapViewHolder(newView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull EditMapViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    public class EditMapViewHolder extends RecyclerView.ViewHolder{
        private MapElementItemBinding mapElementItemBinding;
        public EditMapViewHolder(@NonNull View itemView) {
            super(itemView);
            mapElementItemBinding = MapElementItemBinding.bind(itemView);
        }

        public void bind(MapElementItem mapElementItem){
            mapElementItemBinding.mapElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editorFragment.updateElement(mapElementItem);
                }
            });
        }
    }

    public void Add(MapElementItem mapElementItem){
        list.add(mapElementItem);
        notifyDataSetChanged();
    }
}