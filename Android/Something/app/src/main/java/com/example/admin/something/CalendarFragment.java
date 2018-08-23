package com.example.admin.something;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CalendarFragment extends ViewPageFragment implements View.OnClickListener {
    private FloatingActionButton floatingActionButton;

    public CalendarFragment() {
        icon = R.drawable.ic_calendar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        floatingActionButton = view.findViewById(R.id.FloatingActionButton);

        floatingActionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), AddOrModifySomethingActivity.class);
        startActivity(intent);
    }
}
