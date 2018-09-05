package com.example.admin.something;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends SomethingFragment implements View.OnClickListener {
    private FloatingActionButton floatingActionButton;

    public ListFragment() {
        wheres.add("");
        icon = R.drawable.ic_assignment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.RecyclerView);
        floatingActionButton = view.findViewById(R.id.FloatingActionButton);

        adapter = new SomethingAdapter(this, DBHandler.select(""));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        floatingActionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), AddOrModifySomethingActivity.class);
        intent.putExtra("Action", AddOrModifySomethingActivity.INSERT);
        startActivity(intent);
    }
}
