package com.gp.hmb.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gp.hmb.MainActivity;
import com.gp.hmb.R;
import com.gp.hmb.databinding.ItemChildrenBinding;
import com.gp.hmb.model.Child;

import java.util.List;

public class childrenAdapter extends RecyclerView.Adapter<childrenAdapter.ChildHolder> {

    List<Child> children;

    public childrenAdapter(List<Child> children) {
        this.children = children;
    }

    @NonNull
    @Override
    public ChildHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChildHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_children, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildHolder holder, int position) {
        holder.binding.setChild(children.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("childDateOfBirth", children.get(position).getDateOfBirth());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    class ChildHolder extends RecyclerView.ViewHolder {
        ItemChildrenBinding binding;

        public ChildHolder(@NonNull ItemChildrenBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
