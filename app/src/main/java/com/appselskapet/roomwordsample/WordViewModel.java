package com.appselskapet.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;
import java.util.Objects;

/**
 * Created by haraldellingsen on 22.01.2018.
 */

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWordsByCategory();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    void insert(Word word) {mRepository.insert(word);}

    void sortBy(String item) {
        if (Objects.equals(item, "category")) {
            mAllWords = mRepository.getAllWordsByCategory();
        } else if (Objects.equals(item, "item")) {
            mAllWords = mRepository.getAllWordsByItem();
        }
    }
}
