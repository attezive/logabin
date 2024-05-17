package com.example.logabin.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logabin.R;
import com.example.logabin.databinding.FolderItemBinding;
import com.example.logabin.model.ElementItem;
import com.example.logabin.model.FolderItem;

import java.util.ArrayList;
import java.util.List;


public class FoldersAdapter extends RecyclerView.Adapter<FoldersAdapter.FolderViewHolder> {
    private List<FolderItem> list = new ArrayList<>();

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newView = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_item,
                parent, false);
        return new FolderViewHolder(newView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    public class FolderViewHolder extends RecyclerView.ViewHolder{
        private FolderItemBinding folderItemBinding;
        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
            folderItemBinding = FolderItemBinding.bind(itemView);
        }

        public void bind(FolderItem folderItem){
            folderItemBinding.folderName.setText(folderItem.getFolderName());
            folderItemBinding.folderIcon.setImageResource(folderItem.getIconPath());
            folderItemBinding.rvItems.setLayoutManager(new LinearLayoutManager(folderItemBinding.getRoot().getContext(),
                    RecyclerView.VERTICAL, false));

            ElementsAdapter elementsAdapter = new ElementsAdapter();
            elementsAdapter.Add(new ElementItem("Test"));
            elementsAdapter.Add(new ElementItem("Test"));
            elementsAdapter.Add(new ElementItem("Test"));

            folderItemBinding.rvItems.setAdapter(elementsAdapter);

            folderItemBinding.rvItems.setActivated(false);
            folderItemBinding.rvItems.setVisibility(View.GONE);

            folderItemBinding.folderTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    folderItemBinding.rvItems.setVisibility(
                            folderItemBinding.rvItems.getVisibility() == View.GONE?
                                    View.VISIBLE : View.GONE);
                }
            });
        }
    }

    public void Add(FolderItem folderItem){
        list.add(folderItem);
        notifyDataSetChanged();
    }
}
