package com.example.admin.something;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OtherAdapter extends RecyclerView.Adapter<OtherHolder> {
    private Other[] others;

    public OtherAdapter() {
        others = Other.values();
    }

    @NonNull
    @Override
    public OtherHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(type, viewGroup, false);

        return new OtherHolder(view, type);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherHolder otherHolder, int position) {
        otherHolder.init(others[position]);
    }

    @Override
    public int getItemCount() {
        return others.length;
    }

    @Override
    public int getItemViewType(int position) {
        return others[position].type;
    }
}
