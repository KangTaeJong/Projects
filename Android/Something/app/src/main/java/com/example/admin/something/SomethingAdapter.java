package com.example.admin.something;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SomethingAdapter extends RecyclerView.Adapter<SomethingHolder> {
    private SomethingFragment parent;
    private ArrayList<Something> list;

    public SomethingAdapter(SomethingFragment parent, ArrayList<Something> list) {
        this.parent = parent;
        this.list = list;
    }

    public void refresh(final ArrayList<Something> _list) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                list = _list;
                notifyDataSetChanged();
                Log.d("Adapter", "TCall");
            }
        });
    }

    @NonNull
    @Override
    public SomethingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false);
        return new SomethingHolder(parent, view);
    }

    @Override
    public void onBindViewHolder(@NonNull SomethingHolder somethingHolder, int position) {
        somethingHolder.init(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_something;
    }
}
