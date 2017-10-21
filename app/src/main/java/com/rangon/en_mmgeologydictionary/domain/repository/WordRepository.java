package com.rangon.en_mmgeologydictionary.domain.repository;

import com.rangon.en_mmgeologydictionary.model.Word;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 17/7/17.
 */

public interface WordRepository {
    Observable<Word> getWord(String word, String id);

    Observable<Boolean> updateRecentWord(String word, String id);

    Observable<List<Word>> getLikelyWord(String searchKeyword, int limit, int page);

    Observable<List<Word>> getRecentWord(String[] tableNames, int limit, int page);

    Observable<List<Word>> getRelatedWord(String[] tableNames,String word,int limit);

    Observable<Boolean> deleteWordTables(String[] tableNames);

    /**
     * @param wordIndex a,b,c,..z etc
     * @param page
     * @param size
     * @return
     */
    Observable<List<Word>> getWordList(String wordIndex, int page, int size);
}
