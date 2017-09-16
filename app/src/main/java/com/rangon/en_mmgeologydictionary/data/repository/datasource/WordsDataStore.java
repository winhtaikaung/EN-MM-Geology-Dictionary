package com.rangon.en_mmgeologydictionary.data.repository.datasource;

import com.rangon.en_mmgeologydictionary.model.Word;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public interface WordsDataStore {
    Observable<Word> getWord(String word);

    Observable<List<Word>> getLikelyWord(String searchKeyword);

    /**
     * @param wordIndex a,b,c..etc
     * @param page
     * @param size
     * @return
     */
    Observable<List<Word>> getWordList(String wordIndex, int page, int size);

    Observable<Boolean> insertWordList(List<Word> wordList, String tableName);
}
