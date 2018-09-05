package com.example.admin.something;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends SomethingFragment {
    private static final int MODIFY = 1000;
    private static final int DELETE = 1001;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        inflate(view);

        refresh();
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, MODIFY, 0, R.string.Modify);
        menu.add(0, DELETE, 0, R.string.Delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MODIFY:
                Intent intent = new Intent(getContext(), AddOrModifySomethingActivity.class);
                intent.putExtra("Something", something);
                intent.putExtra("Action", AddOrModifySomethingActivity.MODIFY);
                startActivity(intent);
                break;
            case DELETE:
                Database.delete(something);
                refresh();
                break;
        }

        return true;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_fragment_list;
    }

    @Override
    public void refresh() {
        ((SomethingAdapter)recyclerView.getAdapter()).refresh(Database.select(""));
    }
}
