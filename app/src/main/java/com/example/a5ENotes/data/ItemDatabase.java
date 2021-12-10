package com.example.a5ENotes.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {lst.class,nt.class}, version = 7)
public abstract class ItemDatabase extends RoomDatabase{

    public abstract itemDAO itemDAO();

    public abstract myDAO myDAO();


    private static ItemDatabase INSTANCE;

    public static ItemDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (ItemDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ItemDatabase.class, "item_database")
                            .fallbackToDestructiveMigration()
                            //Following
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}