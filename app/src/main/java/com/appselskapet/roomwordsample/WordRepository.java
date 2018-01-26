package com.appselskapet.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by haraldellingsen on 22.01.2018.
 */

class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWordsByCategory;
    private LiveData<List<Word>> mAllWordsByItem;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWordsByCategory = mWordDao.getAllWordsByCategory();
        mAllWordsByItem = mWordDao.getAllWordsByItem();
    }

    LiveData<List<Word>> getAllWordsByCategory(){
        return mAllWordsByCategory;
    }
    LiveData<List<Word>> getAllWordsByItem(){ return mAllWordsByItem; }

    void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }


    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

    void update(Word word) { new updateAsyncTask(mWordDao).execute(word); }

    private static class updateAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        updateAsyncTask(WordDao dao) { mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.update(words[0]);
            return null;
        }
    }
}
