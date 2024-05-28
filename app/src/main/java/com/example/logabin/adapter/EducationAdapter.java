package com.example.logabin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logabin.R;
import com.example.logabin.databinding.EducationItemBinding;
import com.example.logabin.databinding.FolderItemBinding;
import com.example.logabin.db.model.ElementModel;
import com.example.logabin.model.EducationItem;

import java.util.ArrayList;
import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EducationViewHolder> {
    private List<EducationItem> list = new ArrayList<>();

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newView = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_item,
                parent, false);
        return new EducationViewHolder(newView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    public class EducationViewHolder extends RecyclerView.ViewHolder{
        private EducationItemBinding educationItemBinding;
        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            educationItemBinding = EducationItemBinding.bind(itemView);
        }

        public void bind(EducationItem educationItem){
            educationItemBinding.design.setImageResource(educationItem.getDesignImg());
            educationItemBinding.table.setImageResource(educationItem.getTableImg());
            educationItemBinding.informationText.setText(educationItem.getText());
        }
    }

    public void Add(EducationItem educationItem){
        list.add(educationItem);
        notifyDataSetChanged();
    }
}
