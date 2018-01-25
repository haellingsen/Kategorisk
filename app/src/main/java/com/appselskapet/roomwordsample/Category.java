package com.appselskapet.roomwordsample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by haraldellingsen on 21.01.2018.
 */

@Entity(tableName = "category_table")
public class Category {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int mId;

    @NonNull
    @ColumnInfo(name = "category")
    private String mCategory;

    public Category(@NonNull String category) {this.mCategory = category;}
    public String getCategory() {return this.mCategory;}
    public int getId() {return this.mId;}
}
