package com.rangon.en_mmgeologydictionary.data.repository.datasource;

import com.rangon.en_mmgeologydictionary.data.service.WordDAL;
import com.rangon.en_mmgeologydictionary.model.Word;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public class WordsLocalDataStore implements WordsDataStore {
    private WordDAL mWordDal;

    public WordsLocalDataStore(WordDAL wordDAL) {
        this.mWordDal = wordDAL;
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
        return null;
    }

    @Override
    public Observable<Boolean> insertWordList(List<Word> wordList, String tableName) {
        return Observable.just(mWordDal.bulkInsertWord(wordList, tableName));
    }
}
