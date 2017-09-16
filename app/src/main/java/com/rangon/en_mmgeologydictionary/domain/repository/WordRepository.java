package com.rangon.en_mmgeologydictionary.domain.repository;

import com.rangon.en_mmgeologydictionary.model.Word;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 17/7/17.
 */

public interface WordRepository {
    Observable<Word> getWord(String word);

    Observable<List<Word>> getLikelyWord(String searchKeyword);

    /**
     * @param wordIndex a,b,c,..z etc
     * @param page
     * @param size
     * @return
     */
    Observable<List<Word>> getWordList(String wordIndex, int page, int size);
}
