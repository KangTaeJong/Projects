package com.example.admin.something;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public abstract class SomethingFragment extends ViewPageFragment implements View.OnClickListener {
    protected Something something;
    protected RecyclerView recyclerView;
    protected FloatingActionButton floatingActionButton;

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    protected void inflate(View view) {
        recyclerView = view.findViewById(R.id.RecyclerView);
        floatingActionButton = view.findViewById(R.id.FloatingActionButton);

        recyclerView.setAdapter(new SomethingAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), AddOrModifySomethingActivity.class));
    }

    public void setSomething(Something something) {
        this.something = something;
    }

    public abstract void refresh();
}
