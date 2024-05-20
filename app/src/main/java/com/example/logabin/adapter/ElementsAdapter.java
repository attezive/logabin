package com.example.logabin.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logabin.MainActivity;
import com.example.logabin.R;
import com.example.logabin.databinding.ElementItemBinding;
import com.example.logabin.databinding.FolderItemBinding;
import com.example.logabin.db.model.ElementModel;
import com.example.logabin.fragment.EditorFragment;
import com.example.logabin.model.ElementItem;
import com.example.logabin.model.FolderItem;

import java.util.ArrayList;
import java.util.List;


public class ElementsAdapter extends RecyclerView.Adapter<ElementsAdapter.ElementViewHolder> {
    private List<ElementModel> list = new ArrayList<>();

    @NonNull
    @Override
    public ElementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newView = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_item,
                parent, false);
        return new ElementViewHolder(newView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ElementViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    public class ElementViewHolder extends RecyclerView.ViewHolder{
        private ElementItemBinding elementItemBinding;
        public ElementViewHolder(@NonNull View itemView) {
            super(itemView);
            elementItemBinding = ElementItemBinding.bind(itemView);
        }

        public void bind(ElementModel element){
            elementItemBinding.elementName.setText(element.name);
            elementItemBinding.elementIcon.setImageResource(element.icon);

            elementItemBinding.elementField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditorFragment.setCurrentElementModel(element);
                    MainActivity.setPage(1);
                }
            });
        }
    }

    public void Add(ElementModel elementModel){
        list.add(elementModel);
        notifyDataSetChanged();
    }
}
