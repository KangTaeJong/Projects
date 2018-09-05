package com.example.admin.something;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddOrModifySomethingActivity extends AppCompatActivity implements View.OnClickListener, CheckBox.OnCheckedChangeListener, CalendarView.OnDateChangeListener {
    public static final int INSERT = 0;
    public static final int MODIFY = 1;

    private ViewGroup announceLayout, noTermLayout, startLayout, endLayout;
    private CheckBox announceCheckBox, noTermCheckBox;
    private TextView start, end, term;
    private CalendarView startCalendar, endCalendar;
    private EditText title, explains;
    private Button confirm, cancle;
    private int action;
    private Something something;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_modify_something);

        announceLayout = findViewById(R.id.AnnounceLayout);
        noTermLayout = findViewById(R.id.NoTermLayout);
        startLayout = findViewById(R.id.StartLayout);
        endLayout = findViewById(R.id.EndLayout);
        announceCheckBox = findViewById(R.id.AnnounceCheckBox);
        noTermCheckBox = findViewById(R.id.NoTermCheckBox);
        start = findViewById(R.id.Start);
        end = findViewById(R.id.End);
        term = findViewById(R.id.Term);
        startCalendar = findViewById(R.id.StartCalendar);
        endCalendar = findViewById(R.id.EndCalendar);
        title = findViewById(R.id.Title);
        explains = findViewById(R.id.Explain);
        confirm = findViewById(R.id.Confirm);
        cancle = findViewById(R.id.Cancle);

        announceLayout.setOnClickListener(this);
        noTermLayout.setOnClickListener(this);
        noTermCheckBox.setOnCheckedChangeListener(this);
        confirm.setOnClickListener(this);
        cancle.setOnClickListener(this);
        startCalendar.setOnDateChangeListener(this);
        endCalendar.setOnDateChangeListener(this);

        Intent intent = getIntent();

        action = intent.getIntExtra("Action", INSERT);
        if(action == MODIFY) {
            something = (Something)intent.getSerializableExtra("Something");
            title.setText(something.getTitle());
            explains.setText(something.getExplains());
        }
        else if(action == INSERT) {
            something = new Something();
        }

        announceCheckBox.setChecked(something.isAnnounce());
        noTermCheckBox.setChecked(something.isNoTerm());
        start.setText("Start : " + something.getStart());
        end.setText("End : " + something.getEnd());
        term.setText(something.getTerm());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.AnnounceLayout:
                announceCheckBox.setChecked(!announceCheckBox.isChecked());
                break;
            case R.id.NoTermLayout:
                noTermCheckBox.setChecked(!noTermCheckBox.isChecked());
                break;
            case R.id.Confirm:
                if(!noTermCheckBox.isChecked() && Database.clientToSystem(something.getStart()) > Database.clientToSystem(something.getEnd())) {
                    Toast.makeText(this, "Invalid Term", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(title.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Title is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(announceCheckBox.isChecked()) {
                    something.setBit(something.getBit() | Something.ANNOUNCE);
                }
                else {
                    something.setBit(something.getBit() & ~Something.ANNOUNCE);
                }

                if(noTermCheckBox.isChecked()) {
                    something.setBit(something.getBit() | Something.NO_TERM);
                }
                else {
                    something.setBit(something.getBit() & ~Something.NO_TERM);
                }

                something.setTitle(title.getText().toString());
                something.setExplains(explains.getText().toString());

                switch (action) {
                    case INSERT:
                        Database.insert(something);
                        break;
                    case MODIFY:
                        Database.modify(something);
                        break;
                }
            case R.id.Cancle:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(noTermCheckBox.isChecked()) {
            startLayout.setVisibility(View.GONE);
            endLayout.setVisibility(View.GONE);
            term.setVisibility(View.GONE);
        }
        else {
            startLayout.setVisibility(View.VISIBLE);
            endLayout.setVisibility(View.VISIBLE);
            term.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
        String time = year + " / " + (month + 1) + " / " + day;

        switch (calendarView.getId()) {
            case R.id.StartCalendar:
                start.setText("Start : " + time);
                something.setStart(time);
                break;
            case R.id.EndCalendar:
                end.setText("End : " + time);
                something.setEnd(time);
                break;
        }

        term.setText(something.getTerm());
    }
}
