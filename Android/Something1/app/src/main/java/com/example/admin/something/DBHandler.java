package com.example.admin.something;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.Collections;

public class DBHandler {
    private static String name;
    private static Context context;
    private static SharedPreferences preferences;

    public static ArrayList<Boolean> changeBooleans;

    static {
        changeBooleans = new ArrayList<>();
    }

    public static void init(Context _context) {
        name = "Something";
        context = _context;
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);

        if(!preferences.getBoolean("isVisited", false)) {
            reset();

            NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("Something", "Something", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isVisited", true);
            editor.commit();
        }
    }

    public static void reset() {
        SQLiteDatabase database = context.openOrCreateDatabase(name, context.MODE_PRIVATE, null);
        SQLiteStatement stmt1 = database.compileStatement("DROP TABLE IF EXISTS Something");

        stmt1.execute();
        stmt1.close();

        stmt1 = database.compileStatement("CREATE TABLE IF NOT EXISTS Something(ID INTEGER PRIMARY KEY AUTOINCREMENT, StartTime LONG NOT NULL, EndTime LONG NOT NULL, Title TEXT NOT NULL, Explains TEXT, Bit LONG NOT NULL)");
        stmt1.execute();
        stmt1.close();

        database.close();

        insert(new Something());
    }

    public static void insert(Something something) {
        SQLiteDatabase database = context.openOrCreateDatabase(name, Context.MODE_PRIVATE, null);
        SQLiteStatement stmt1 = database.compileStatement("INSERT INTO Something(StartTime, EndTime, Title, Explains, Bit) VALUES (?, ?, ?, ?, ?)");

        stmt1.bindLong(1, TimeHandler.clientToSystem(something.getStart()));
        stmt1.bindLong(2, TimeHandler.clientToSystem(something.getEnd()));
        stmt1.bindString(3, something.getTitle());
        stmt1.bindString(4, something.getExplains());
        stmt1.bindLong(5, something.getBit());
        stmt1.executeInsert();

        stmt1.close();
        database.close();

        for(int i = 0;i < changeBooleans.size();i++) {
            changeBooleans.set(i, true);
        }
    }

    public static void delete(Something something) {
        SQLiteDatabase database = context.openOrCreateDatabase(name, Context.MODE_PRIVATE, null);
        SQLiteStatement stmt = database.compileStatement("DELETE FROM Something WHERE ID = ?");

        stmt.bindLong(1, something.getId());
        stmt.executeUpdateDelete();

        stmt.close();
        database.close();

        for(int i = 0;i < changeBooleans.size();i++) {
            changeBooleans.set(i, true);
        }
    }

    public static void modify(Something something) {
        SQLiteDatabase database = context.openOrCreateDatabase(name, Context.MODE_PRIVATE, null);
        SQLiteStatement stmt = database.compileStatement("UPDATE Something SET StartTime = ?, EndTime = ?, Title = ?, Explains = ?, Bit = ? WHERE ID = ?");

        stmt.bindLong(1, TimeHandler.clientToSystem(something.getStart()));
        stmt.bindLong(2, TimeHandler.clientToSystem(something.getEnd()));
        stmt.bindString(3, something.getTitle());
        stmt.bindString(4, something.getExplains());
        stmt.bindLong(5, something.getBit());
        stmt.bindLong(6, something.getId());
        stmt.executeUpdateDelete();

        stmt.close();
        database.close();


        for(int i = 0;i < changeBooleans.size();i++) {
            changeBooleans.set(i, true);
        }
    }

    public static ArrayList<Something> select(String where) {
        SQLiteDatabase database = context.openOrCreateDatabase(name, context.MODE_PRIVATE, null);
        Cursor cursor1 = database.rawQuery("SELECT * FROM Something" + where, null);

        ArrayList<Something> list = new ArrayList<>();
        while(cursor1.moveToNext()) {
            long id = cursor1.getLong(cursor1.getColumnIndex("ID"));
            long bit = cursor1.getLong(cursor1.getColumnIndex("Bit"));
            String start = TimeHandler.systemToClient(cursor1.getLong(cursor1.getColumnIndex("StartTime")));
            String end = TimeHandler.systemToClient(cursor1.getLong(cursor1.getColumnIndex("EndTime")));
            String title = cursor1.getString(cursor1.getColumnIndex("Title"));
            String explains = cursor1.getString(cursor1.getColumnIndex("Explains"));

            list.add(new Something(id, bit, start, end, title, explains));
        }

        cursor1.close();
        database.close();

        Collections.sort(list);
        return list;
    }

    public static void setSetting(Other other, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(other.name(), value);
        editor.commit();
    }

    public static boolean isChecked(Other other) {
        return preferences.getBoolean(other.name(), false);
    }
}
