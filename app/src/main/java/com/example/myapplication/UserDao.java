package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAll();
    //OnConflictStrategy s'ulitza en cas de que la PK ja existeix.
    //No es necesari.
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertUser(User element);
}
