package com.example.a5ENotes.data;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class lst {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int UID;

    private String lstname;

    private String lstweight;

    private String lstValue;

    private boolean lstAdded;

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getLstname() {
        return lstname;
    }

    public void setLstname(String lstname) {
        this.lstname = lstname;
    }

    public String getLstweight() {
        return lstweight;
    }

    public void setLstweight(String lstweight) {
        this.lstweight = lstweight;
    }

    public String getLstValue() {
        return lstValue;
    }

    public void setLstValue(String lstValue) {
        this.lstValue = lstValue;
    }

    public boolean isLstAdded() {
        return lstAdded;
    }

    public void setLstAdded(boolean lstAdded) {
        this.lstAdded = lstAdded;
    }
}