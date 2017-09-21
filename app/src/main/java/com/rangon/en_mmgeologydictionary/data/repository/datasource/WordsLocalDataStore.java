package com.rangon.en_mmgeologydictionary.data.repository.datasource;

import com.rangon.en_mmgeologydictionary.data.cache.AppDataCache;
import com.rangon.en_mmgeologydictionary.data.service.WordDAL;
import com.rangon.en_mmgeologydictionary.model.Word;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public class WordsLocalDataStore implements WordsDataStore {
    private WordDAL mWordDal;
    private AppDataCache mAppDataCache;

    public WordsLocalDataStore(WordDAL wordDAL, AppDataCache cache) {
        this.mWordDal = wordDAL;
        this.mAppDataCache = cache;
    }


    @Override
    public Observable<Word> getWord(String word) {
        return Observable.just(mWordDal.getByWord(word));
    }

    @Override
    public Observable<List<Word>> getLikelyWord(String searchKeyword) {
        return Observable.just(mWordDal.getLikelyWords(searchKeyword));
    }

    @Override
    public Observable<List<Word>> getWordList(String wordIndex, int page, int size) {
        // TODO fetch wordlist locally
        return Observable.just(new ArrayList<Word>());
    }

    @Override
    public Observable<Boolean> insertWordList(List<Word> wordList, String tableName) {
        return Observable.just(mWordDal.bulkInsertWord(wordList, tableName));
    }
}
