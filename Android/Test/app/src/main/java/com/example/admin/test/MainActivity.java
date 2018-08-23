package com.example.admin.test;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
//
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("Hi");
//        registerReceiver(new MyReceiver(), filter);
    }

    @Override
    public void onClick(View view) {
        sendBroadcast(new Intent("Hi"));
    }
}
