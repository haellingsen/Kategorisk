package com.appselskapet.roomwordsample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by haraldellingsen on 22.01.2018.
 */

@Dao
public interface WordDao {
    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table ORDER BY status DESC, category_id ASC, word ASC")
    LiveData<List<Word>> getAllWordsByCategory();

    @Query("SELECT * FROM word_table ORDER BY status DESC, word ASC")
    LiveData<List<Word>> getAllWordsByItem();

}