package com.example.greendao.allsignin.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.greendao.allsignin.model.ResponseDatum;

import java.util.List;

@Dao
public interface GreenDao {


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(ResponseDatum result);


        @Query("SELECT * FROM green_table")
        List<ResponseDatum> getAllFav();

        @Delete()
        void delete(ResponseDatum result);





}
