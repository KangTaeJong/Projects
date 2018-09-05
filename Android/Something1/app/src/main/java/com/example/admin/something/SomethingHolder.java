package com.example.admin.something;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class SomethingHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private SomethingFragment parent;
    private Something something;
    private ViewGroup layout;
    private TextView term;
    private EditText title, explains;

    public SomethingHolder(SomethingFragment parent, View view) {
        super(view);
        this.parent = parent;

        layout = view.findViewById(R.id.MainLayout);
        term = view.findViewById(R.id.Term);
        title = view.findViewById(R.id.Title);
        explains = view.findViewById(R.id.Explain);

        layout.setOnClickListener(this);
        layout.setOnLongClickListener(this);

        title.setFocusable(false);
        title.setOnClickListener(this);
        title.setOnLongClickListener(this);

        explains.setFocusable(false);
        explains.setOnClickListener(this);
        explains.setOnLongClickListener(this);

        parent.registerForContextMenu(layout);
    }

    public void init(Something something) {
        this.something = something;

        if(something.isNoTerm()) {
            term.setVisibility(View.GONE);
        }
        else {
            term.setText(something.getTerm());
        }

        title.setText(something.getTitle());

        if(something.getExplains().isEmpty()) {
            explains.setVisibility(View.GONE);
        }
        else {
            explains.setText(something.getExplains());
        }

        Log.d("Holder", "TCall");
    }

    @Override
    public void onClick(View view) {
        parent.setSomething(something);
        layout.showContextMenu();
    }

    @Override
    public boolean onLongClick(View view) {
        parent.setSomething(something);
        layout.showContextMenu();
        return true;
    }
}
