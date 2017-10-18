package com.rangon.en_mmgeologydictionary.domain.repository;

import com.rangon.en_mmgeologydictionary.model.Word;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 17/7/17.
 */

public interface WordRepository {
    Observable<Word> getWord(String word, String id);

    Observable<List<Word>> getLikelyWord(String searchKeyword);

    Observable<Boolean> deleteWordTables(String[] tableNames);

    /**
     * @param wordIndex a,b,c,..z etc
     * @param page
     * @param size
     * @return
     */
    Observable<List<Word>> getWordList(String wordIndex, int page, int size);
}
