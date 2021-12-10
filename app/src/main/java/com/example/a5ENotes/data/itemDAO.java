package com.example.a5ENotes.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface itemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(lst list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<lst> lists);

    @Query("Select * from lst")
    public List<lst> getAllTasks();

    @Query("Update lst Set lstAdded = :added WHERE UID = :id")
    void UpdateAdded(Boolean added, int id);

    @Query("Select * from lst where lstAdded = 1")
    public List<lst> getAllTrue();

    @Delete
    public void delete(lst list);

    @Delete
    public void deleteLists(List<lst> lists);

    @Update
    public void update(lst list);

    @Update
    public void updateItems(List<lst> lists);

}


