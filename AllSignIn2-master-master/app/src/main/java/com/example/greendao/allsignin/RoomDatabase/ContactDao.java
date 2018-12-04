package com.example.greendao.allsignin.RoomDatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.greendao.allsignin.model.ResponseDatum;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ResponseDatum result);


    @Query("SELECT * FROM movie_table")
    LiveData<List<ResponseDatum>> getAllFav();

//    @Query("DELETE FROM movie_table WHERE id=:id")
//    void delete(int id);

}
