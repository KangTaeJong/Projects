package com.example.admin.something;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Database {
    private static String name;
    private static Context context;
    private static SimpleDateFormat time;
    private static SharedPreferences preferences;

    static {
        name = "Something";
        time = new SimpleDateFormat("yyyy / M / d");
    }

    public static void init(Context _context) {
        context = _context;
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);

        if(!preferences.getBoolean("isVisited", false)) {
            reset();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isVisited", true);
            editor.commit();
        }
    }

    public static String systemToClient(long _time) {
        return time.format(new Date(_time));
    }

    public static long clientToSystem(String _time) {
        try {
            return time.parse(_time).getTime();
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
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

        stmt1.bindLong(1, clientToSystem(something.getStart()));
        stmt1.bindLong(2, clientToSystem(something.getEnd()));
        stmt1.bindString(3, something.getTitle());
        stmt1.bindString(4, something.getExplains());
        stmt1.bindLong(5, something.getBit());
        stmt1.executeInsert();

        stmt1.close();
        database.close();
    }

    public static void delete(Something something) {
        SQLiteDatabase database = context.openOrCreateDatabase(name, Context.MODE_PRIVATE, null);
        SQLiteStatement stmt = database.compileStatement("DELETE FROM Something WHERE ID = ?");

        stmt.bindLong(1, something.getId());
        stmt.executeUpdateDelete();

        stmt.close();
        database.close();
    }

    public static void modify(Something something) {
        SQLiteDatabase database = context.openOrCreateDatabase(name, Context.MODE_PRIVATE, null);
        SQLiteStatement stmt = database.compileStatement("UPDATE Something SET StartTime = ?, EndTime = ?, Title = ?, Explains = ?, Bit = ? WHERE ID = ?");

        stmt.bindLong(1, clientToSystem(something.getStart()));
        stmt.bindLong(2, clientToSystem(something.getEnd()));
        stmt.bindString(3, something.getTitle());
        stmt.bindString(4, something.getExplains());
        stmt.bindLong(5, something.getBit());
        stmt.bindLong(6, something.getId());
        stmt.executeUpdateDelete();

        stmt.close();
        database.close();
    }

    public static ArrayList<Something> select(String where) {
        SQLiteDatabase database = context.openOrCreateDatabase(name, context.MODE_PRIVATE, null);
        Cursor cursor1 = database.rawQuery("SELECT * FROM Something" + where, null);

        ArrayList<Something> list = new ArrayList<>();
        while(cursor1.moveToNext()) {
            long id = cursor1.getLong(cursor1.getColumnIndex("ID"));
            long bit = cursor1.getLong(cursor1.getColumnIndex("Bit"));
            String start = systemToClient(cursor1.getLong(cursor1.getColumnIndex("StartTime")));
            String end = systemToClient(cursor1.getLong(cursor1.getColumnIndex("EndTime")));
            String title = cursor1.getString(cursor1.getColumnIndex("Title"));
            String explains = cursor1.getString(cursor1.getColumnIndex("Explains"));

            list.add(new Something(id, bit, start, end, title, explains));
        }

        cursor1.close();
        database.close();

        Collections.sort(list);
        return list;
    }

    public static boolean getSetting(OtherFragment.Enums enums) {
        return preferences.getBoolean(enums.name(), false);
    }

    public static void setSetting(OtherFragment.Enums enums, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(enums.name(), value);
        editor.commit();
    }
}
