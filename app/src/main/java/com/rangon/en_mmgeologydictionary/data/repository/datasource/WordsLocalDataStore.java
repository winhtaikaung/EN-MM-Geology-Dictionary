package com.rangon.en_mmgeologydictionary.data.repository.datasource;

import java.util.List;

import com.rangon.en_mmgeologydictionary.data.service.WordDAL;
import io.reactivex.Observable;
import com.rangon.en_mmgeologydictionary.model.Word;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public class WordsLocalDataStore implements WordsDataStore {
    private WordDAL wordDAL;

    public WordsLocalDataStore() {
        wordDAL = new WordDAL();
    }


    @Override
    public Observable<Word> getWord(String word) {
        return Observable.just(wordDAL.getByWord(word));
    }

    @Override
    public Observable<List<Word>> getLikelyWord(String searchKeyword) {
        return Observable.just(wordDAL.getLikelyWords(searchKeyword));
    }

    @Override
    public Observable<List<Word>> getWordList(String wordIndex, int page, int size) {
        return null;
    }

    @Override
    public Observable<Boolean> insertWordList(List<Word> wordList, String tableName) {
        return Observable.just(wordDAL.bulkInsertWord(wordList, tableName));
    }
}
