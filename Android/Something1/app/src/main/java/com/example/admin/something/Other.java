package com.example.admin.something;

public enum Other {
    Developer(R.drawable.ic_person, R.layout.item_other_no_switch, "Developer"),
    DB_Reset(R.drawable.ic_storage, R.layout.item_other_no_switch, "DB Reset"),
    Auto_Start(R.drawable.ic_run, R.layout.item_other, "Auto Run");

    final int icon;
    final int type;
    final String title;

    Other(int icon, int type, String title) {
        this.icon = icon;
        this.type = type;
        this.title = title;
    }
}
