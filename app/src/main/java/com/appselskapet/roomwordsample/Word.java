package com.appselskapet.roomwordsample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by haraldellingsen on 21.01.2018.
 */

@Entity(tableName = "word_table")
public class Word {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int mId = 0;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    @ColumnInfo(name = "category_id")
    private int mCategoryId;

    @ColumnInfo(name = "status")
    private int mStatus;

    public Word(@NonNull String word, int categoryId) {
        this.mWord = word;
        this.mCategoryId = categoryId;
        mStatus = 0;
    }

    public void setCategoryId(int categoryId) {this.mCategoryId = categoryId;}
    public void setStatus(int status) {this.mStatus = status;}
    public void setDone() {this.mStatus = 1;}

    public int getId() {return this.mId;}
    public String getWord() {return this.mWord;}
    public int getCategoryId() {return this.mCategoryId;}
    public int getStatus() {return this.mStatus;}

    public void toggleDone() {
        if (this.mStatus == 0) {
            this.mStatus = 1;
        } else if (this.mStatus == 1) {
            this.mStatus = 0;
        }
    }
}
