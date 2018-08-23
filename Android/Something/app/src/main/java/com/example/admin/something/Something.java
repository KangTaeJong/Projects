package com.example.admin.something;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Something implements Serializable, Comparable<Something> {
    public static final long ANNOUNCE = 1 << 0;
    public static final long NO_TERM = 1 << 1;
    private long id, bit;
    private String start, end, title, explains;

    public Something() {
        id = -1;
        bit = 0;
        start = end = TimeHandler.systemToClient(System.currentTimeMillis());
        title = "Hello World";
        explains = "Tab the Something";
    }

    public Something(long id, long bit, String start, String end, String title, String explains) {
        this.id = id;
        this.bit = bit;
        this.start = start;
        this.end = end;
        this.title = title;
        this.explains = explains;
    }

    public String getTerm() {
        return start.equals(end) ? start : start + " ~ " + end;
    }

    public boolean isAnnounce() {
        return (bit & ANNOUNCE) != 0;
    }

    public boolean isNoTerm() {
        return (bit & NO_TERM) != 0;
    }

    @Override
    public int compareTo(@NonNull Something something) {
        if(this.isAnnounce() && !something.isAnnounce()) {
            return -1;
        }
        else if(!this.isAnnounce() && something.isAnnounce()) {
            return 1;
        }
        else if(this.isAnnounce() && something.isAnnounce()) {
            if(this.isNoTerm() && !something.isNoTerm()) {
                return -1;
            }
            else {
                return 1;
            }
        }

        long startTime = TimeHandler.clientToSystem(start);
        long otherStartTime = TimeHandler.clientToSystem(something.start);
        if(startTime != otherStartTime) return Long.compare(startTime, otherStartTime);

        long endTime = TimeHandler.clientToSystem(end);
        long otherEndTime = TimeHandler.clientToSystem(something.end);
        if(endTime != otherEndTime) return Long.compare(endTime, otherEndTime);

        if(!title.equals(something.title)) return title.compareTo(something.title);

        return Long.compare(id, something.id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBit() {
        return bit;
    }

    public void setBit(long bit) {
        this.bit = bit;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }
}
