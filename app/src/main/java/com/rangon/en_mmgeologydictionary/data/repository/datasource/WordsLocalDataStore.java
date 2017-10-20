package com.rangon.en_mmgeologydictionary.data.repository.datasource;

import com.rangon.en_mmgeologydictionary.data.DAL.WordDAL;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCache;
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
    public Observable<Word> getWord(String word, String id) {
        return Observable.just(mWordDal.getByWord(word, id));
    }

    @Override
    public Observable<List<Word>> getLikelyWord(String searchKeyword,int limit,int page) {
        return Observable.just(mWordDal.getLikelyWords(searchKeyword,limit,page));
    }

    @Override
    public Observable<Boolean> deleteWordTables(String[] tableNames) {
        return Observable.just(mWordDal.deleteWordTable(tableNames));
    }

    @Override
    public Observable<Boolean> updateRecentWord(String word, String id) {
        return Observable.just(mWordDal.updateRecent(word, id));
    }

    @Override
    public Observable<List<Word>> getRecentWord(String[] tableNames, int limit, int page) {
        return Observable.just(mWordDal.getRecentFavWords(tableNames, limit, page));
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
