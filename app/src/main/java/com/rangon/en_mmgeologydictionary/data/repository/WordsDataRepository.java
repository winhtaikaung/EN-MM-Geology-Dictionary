package com.rangon.en_mmgeologydictionary.data.repository;

import com.rangon.en_mmgeologydictionary.data.repository.datasource.WordDataStoreFactory;
import com.rangon.en_mmgeologydictionary.data.service.WordDAL;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public class WordsDataRepository implements WordRepository {

    private final WordDataStoreFactory mWordDataStoreFactory;
    private final WordDAL mWordDal;

    /**
     * @param wordsDataStoreFactory
     */
    public WordsDataRepository(WordDataStoreFactory wordsDataStoreFactory, WordDAL wordDAL) {
        this.mWordDataStoreFactory = wordsDataStoreFactory;
        this.mWordDal = wordDAL;
    }

    /**
     * @param word
     * @return
     */
    @Override
    public Observable<Word> getWord(String word) {
        return mWordDataStoreFactory.create(mWordDal).getWord(word).map(word1 -> word1);
    }

    /**
     * @param searchKeyword
     * @return
     */
    @Override
    public Observable<List<Word>> getLikelyWord(String searchKeyword) {
        return mWordDataStoreFactory.create(mWordDal).getLikelyWord(searchKeyword).map(words -> words);
    }

    /**
     * @param page
     * @param size
     * @return
     */
    @Override
    public Observable<List<Word>> getWordList(String wordIndex, int page, int size) {
        return mWordDataStoreFactory.create(mWordDal).getWordList(wordIndex, page, size).map(words -> words);
    }
}
