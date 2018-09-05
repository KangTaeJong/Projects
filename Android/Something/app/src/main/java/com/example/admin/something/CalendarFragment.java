package com.example.admin.something;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CalendarFragment extends SomethingFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        inflate(view);
        return view;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_fragment_calendar;
    }

    @Override
    public void refresh() {

    }
}
