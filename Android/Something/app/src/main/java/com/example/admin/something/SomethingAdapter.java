package com.example.admin.something;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SomethingAdapter extends RecyclerView.Adapter<SomethingAdapter.Holder> {
    private SomethingFragment parent;
    private ArrayList<Something> somethings;

    public SomethingAdapter(SomethingFragment parent) {
        this.parent = parent;
        somethings = new ArrayList<>();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false);

        switch (viewType) {
            case R.layout.item_something:
                return new SomethingHolder(view);
            case R.layout.item_no_term_something:
                return new NoTermHolder(view);
            case R.layout.item_no_explains:
                return new NoExplainsHolder(view);
            case R.layout.item_no_term_no_explains:
                return new NoTermNoExplainsHolder(view);
            default:
                return new SomethingHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Holder somethingHolder, int position) {
        somethingHolder.init(somethings.get(position));
    }

    @Override
    public int getItemCount() {
        return somethings.size();
    }

    @Override
    public int getItemViewType(int position) {
        Something something = somethings.get(position);

        if(something.isNoTerm() && something.getExplains().isEmpty()) {
            return R.layout.item_no_term_no_explains;
        }
        else if(something.isNoTerm()) {
            return R.layout.item_no_term_something;
        }
        else if(something.getExplains().isEmpty()){
            return R.layout.item_no_explains;
        }
        else {
            return R.layout.item_something;
        }
    }

    public void refresh(ArrayList<Something> somethings) {
        this.somethings = somethings;
        notifyDataSetChanged();
    }

    public abstract class Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        protected Something something;
        protected ViewGroup layout;

        public Holder(View view) {
            super(view);

            layout = view.findViewById(R.id.MainLayout);

            layout.setOnClickListener(this);
            layout.setOnLongClickListener(this);

            parent.registerForContextMenu(layout);
        }

        public void init(Something something) {
            this.something = something;
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

    public class SomethingHolder extends Holder {
        private TextView term;
        private EditText title, explains;

        public SomethingHolder(View view) {
            super(view);

            term = view.findViewById(R.id.Term);
            title = view.findViewById(R.id.Title);
            explains = view.findViewById(R.id.Explain);

            title.setFocusable(false);
            title.setOnClickListener(this);
            title.setOnLongClickListener(this);

            explains.setFocusable(false);
            explains.setOnClickListener(this);
            explains.setOnLongClickListener(this);
        }

        @Override
        public void init(Something something) {
            super.init(something);

            term.setText(something.getTerm());
            title.setText(something.getTitle());
            explains.setText(something.getExplains());
        }
    }

    public class NoTermHolder extends Holder {
        private EditText title, explains;

        public NoTermHolder(View view) {
            super(view);

            title = view.findViewById(R.id.Title);
            explains = view.findViewById(R.id.Explain);

            title.setFocusable(false);
            title.setOnClickListener(this);
            title.setOnLongClickListener(this);

            explains.setFocusable(false);
            explains.setOnClickListener(this);
            explains.setOnLongClickListener(this);
        }

        @Override
        public void init(Something something) {
            super.init(something);

            title.setText(something.getTitle());
            explains.setText(something.getExplains());
        }
    }

    public class NoTermNoExplainsHolder extends Holder {
        private EditText title;

        public NoTermNoExplainsHolder(View view) {
            super(view);
            title = view.findViewById(R.id.Title);

            title.setFocusable(false);
            title.setOnClickListener(this);
            title.setOnLongClickListener(this);
        }

        @Override
        public void init(Something something) {
            super.init(something);
            title.setText(something.getTitle());
            layout.setBackgroundColor(0xFFFFFFFF);
        }
    }

    public class NoExplainsHolder extends Holder {
        private TextView term;
        private EditText title;

        public NoExplainsHolder(View view) {
            super(view);

            title = view.findViewById(R.id.Title);
            term = view.findViewById(R.id.Term);

            title.setFocusable(false);
            title.setOnClickListener(this);
            title.setOnLongClickListener(this);
        }

        @Override
        public void init(Something something) {
            super.init(something);

            term.setText(something.getTerm());
            title.setText(something.getTitle());
        }
    }
}
