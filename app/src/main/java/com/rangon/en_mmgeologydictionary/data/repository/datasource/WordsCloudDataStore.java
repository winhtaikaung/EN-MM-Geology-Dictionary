package com.rangon.en_mmgeologydictionary.data.repository.datasource;

import com.rangon.en_mmgeologydictionary.data.cache.AppDataCache;
import com.rangon.en_mmgeologydictionary.data.service.WordDAL;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.services.WordService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.rangon.en_mmgeologydictionary.network.RestClient.getRetrofit;

/**
 * Created by win on 9/9/17.
 */

public class WordsCloudDataStore implements WordsDataStore {

    private WordDAL wordDAL;
    private AppDataCache mCache;

    public WordsCloudDataStore(WordDAL dal, AppDataCache cache) {
        this.wordDAL = dal;
        this.mCache = cache;
    }

    @Override
    public Observable<Word> getWord(String word, String id) {
        return null;
    }

    @Override
    public Observable<List<Word>> getLikelyWord(String searchKeyword) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteWordTables(String[] tableNames) {
        return null;
    }

    @Override
    public Observable<Boolean> updateRecentWord(String word, String id) {
        return null;
    }

    @Override
    public Observable<List<Word>> getWordList(String wordIndex, int page, int size) {
        if (wordIndex.equalsIgnoreCase("last")) {
            mCache.setCached();
            return Observable.just(new ArrayList<Word>());
        } else {

            return getRetrofit().create(WordService.class).getWordList(wordIndex, page, size)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(wordListResponse -> wordListResponse.getData())
                    .doOnNext(words ->
                            wordDAL.bulkInsertWord(words, wordIndex)
                    );
        }
    }

    @Override
    public Observable<Boolean> insertWordList(List<Word> wordList, String tableName) {
        return null;
    }
}
