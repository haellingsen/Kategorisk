package com.appselskapet.roomwordsample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by haraldellingsen on 22.01.2018.
 */

@Database(entities = {Word.class,Category.class}, version = 9)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            Word word = new Word("Bønner",4);
            mDao.insert(word);
            word = new Word("Erter",4);
            mDao.insert(word);
            word = new Word("Sopp",1);
            mDao.insert(word);
            word = new Word("Melk",3);
            mDao.insert(word);
            word = new Word("Fisk",2);
            mDao.insert(word);
            word = new Word("Egg",3);
            mDao.insert(word);
            word = new Word("Pofiber",5);
            mDao.insert(word);
            word = new Word("Epler",1);
            mDao.insert(word);
            word = new Word("Pærer",1);
            mDao.insert(word);
            word = new Word("Salami",2);
            mDao.insert(word);
            word = new Word("Rømme",3);
            mDao.insert(word);
            return null;
        }
    }

}
