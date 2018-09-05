package com.example.admin.something;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class OtherFragment extends ViewPageFragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);

        recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerView.setAdapter(new Adapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_fragment_other;
    }

    enum Enums {
        Developer(R.drawable.ic_person, R.layout.item_other, "Developer"),
        DB_Reset(R.drawable.ic_storage, R.layout.item_other, "Database Reset"),
        Auto_Run(R.drawable.ic_run, R.layout.item_other_switch,"Auto Run");

        int icon, viewType;
        String title;

        Enums(int icon, int viewType, String title) {
            this.icon = icon;
            this.viewType = viewType;
            this.title = title;
        }
    }

    public class Other extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected Enums enums;
        protected ViewGroup layout;
        protected ImageView image;
        protected TextView title;

        public Other(View view) {
            super(view);

            layout = view.findViewById(R.id.MainLayout);
            image = view.findViewById(R.id.Image);
            title = view.findViewById(R.id.Title);

            layout.setOnClickListener(this);
        }

        public void init(Enums enums) {
            this.enums = enums;

            image.setImageResource(enums.icon);
            title.setText(enums.title);
        }

        @Override
        public void onClick(View v) {
            switch (enums) {
                case Developer:
                    Snackbar.make(v, "Tae Jong Kang", Snackbar.LENGTH_SHORT).show();
                    break;
                case DB_Reset:
                    Snackbar.make(v, "Are you sure?", Snackbar.LENGTH_SHORT).setActionTextColor(0xFFFF0000).setAction("Do", new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            Database.reset();
                        }
                    }).show();
                    break;
            }
        }
    }

    public class SwitchOther extends Other implements Switch.OnCheckedChangeListener {
        protected Switch aSwitch;

        public SwitchOther(View view) {
            super(view);

            aSwitch = view.findViewById(R.id.Switch);
            aSwitch.setOnCheckedChangeListener(this);
        }

        @Override
        public void init(Enums enums) {
            super.init(enums);

            aSwitch.setChecked(Database.getSetting(enums));
        }

        @Override
        public void onClick(View v) {
            aSwitch.setChecked(!aSwitch.isChecked());
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                getContext().startService(new Intent(getContext(), MyService.class));
            }
            else {
                getContext().stopService(new Intent(getContext(), MyService.class));
            }

            Database.setSetting(enums, isChecked);
        }
    }
    public class Adapter extends RecyclerView.Adapter<Other> {
        private Enums[] enums;

        public Adapter() {
            enums = Enums.values();
        }

        @NonNull
        @Override
        public Other onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false);

            switch (viewType) {
                case R.layout.item_other:
                    return new Other(view);
                case R.layout.item_other_switch:
                    return new SwitchOther(view);
                default:
                    return new Other(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull Other other, int position) {
            other.init(enums[position]);
        }

        @Override
        public int getItemCount() {
            return enums.length;
        }

        @Override
        public int getItemViewType(int position) {
            return enums[position].viewType;
        }
    }
}
