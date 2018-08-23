package com.example.admin.something;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public abstract class SomethingFragment extends ViewPageFragment {
    private static final int MODIFY = 1000;
    private static final int DELETE = 1001;
    protected static ArrayList<String> wheres;

    protected int index;
    protected RecyclerView recyclerView;
    protected SomethingAdapter adapter;
    protected Something something;

    static {
        wheres = new ArrayList<>();
    }

    public SomethingFragment() {
        index = wheres.size();
        DBHandler.changeBooleans.add(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(DBHandler.changeBooleans.get(index)) {
            adapter.refresh(DBHandler.select(wheres.get(index)));
            DBHandler.changeBooleans.set(index, false);
            Log.d("onResume", "TCall");
        }
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
                DBHandler.delete(something);
                break;
        }

        onResume();
        return true;
    }

    public void setSomething(Something something) {
        this.something = something;
    }
}
