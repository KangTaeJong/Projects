package com.example.admin.something;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences preferences = context.getSharedPreferences("Something", Context.MODE_PRIVATE);
        SQLiteDatabase database = context.openOrCreateDatabase("Something", Context.MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT COUNT(*) AS N FROM Something", null);

        int count = 0;
        if(cursor.moveToNext()) {
            count = cursor.getInt(cursor.getColumnIndex("N"));
        }

        if(preferences.getBoolean(OtherFragment.Enums.Auto_Run.name(), true) && count > 0) {
            context.startActivity(new Intent(context, MainActivity.class));
        }

        database.close();
        cursor.close();
    }
}
