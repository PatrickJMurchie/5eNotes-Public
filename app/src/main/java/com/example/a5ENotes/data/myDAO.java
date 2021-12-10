package com.example.a5ENotes.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface myDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(nt note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertall(List<nt> notes);

    @Query("Select * from nt")
    public List<nt> getAllNotes();

    @Query("Update nt Set name = :n WHERE UID = :id")
    void UpdateName(String n, int id);

    @Query("Update nt Set disc = :d WHERE UID = :id")
    void UpdateDisc(String d, int id);

    @Query("Delete from nt WHERE UID = :id")
    void DeleteNote(int id);
    @Delete
    public void delete(nt note);

    @Delete
    public void deleteNotes(List<nt> notes);

    @Update
    public void update(nt note);

    @Update
    public void updateNotes(List<nt> notes);

}
