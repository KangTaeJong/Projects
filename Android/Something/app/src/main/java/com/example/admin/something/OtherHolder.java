package com.example.admin.something;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class OtherHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Switch.OnCheckedChangeListener {
    private int viewType;
    private Other other;
    private ViewGroup layout;
    private ImageView image;
    private TextView title;
    private Switch aSwitch;

    public OtherHolder(View view, int viewType) {
        super(view);
        this.viewType = viewType;

        layout = view.findViewById(R.id.MainLayout);
        image = view.findViewById(R.id.Image);
        title = view.findViewById(R.id.Title);

        if(viewType == R.layout.item_other) {
            aSwitch = view.findViewById(R.id.Switch);
            aSwitch.setOnCheckedChangeListener(this);
        }
    }

    public void init(Other other) {
        this.other = other;

        layout.setOnClickListener(this);
        image.setImageResource(other.icon);
        title.setText(other.title);
        if(viewType == R.layout.item_other) {
            aSwitch.setChecked(DBHandler.isChecked(other));
        }
    }

    @Override
    public void onClick(View view) {
        switch (other) {
            case Developer:
                Snackbar.make(layout, "TaeJong", Snackbar.LENGTH_SHORT).show();
                break;
            case DB_Reset:
                Snackbar.make(layout, "Do reset?", Snackbar.LENGTH_LONG).setAction("Do", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBHandler.reset();
                    }
                }).setActionTextColor(0xFFFF0000).show();
                break;
            case Auto_Start:
                aSwitch.setChecked(!aSwitch.isChecked());
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (other) {
            case Auto_Start:
                DBHandler.setSetting(other, aSwitch.isChecked());
                break;
        }
    }
}
