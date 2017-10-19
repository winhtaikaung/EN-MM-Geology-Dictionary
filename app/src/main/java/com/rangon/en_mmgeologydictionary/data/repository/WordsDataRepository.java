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
    public Observable<Word> getWord(String word, String id) {
        return mWordDataStoreFactory.create(mWordDal).getWord(word, id).map(word1 -> word1);
    }

    @Override
    public Observable<Boolean> updateRecentWord(String word, String id) {
        return null;
    }

    /**
     * @param searchKeyword
     * @return
     */
    @Override
    public Observable<List<Word>> getLikelyWord(String searchKeyword) {
        return mWordDataStoreFactory.create(mWordDal).getLikelyWord(searchKeyword).map(words -> words);
    }

    @Override
    public Observable<List<Word>> getRecentWord(String[] tableNames, int limit, int page) {
        return mWordDataStoreFactory.create(mWordDal).getRecentWord(tableNames, limit, page).map(words -> words);
    }

    @Override
    public Observable<Boolean> deleteWordTables(String[] tableName) {
        return mWordDataStoreFactory.create(mWordDal).deleteWordTables(tableName).map(aBoolean -> aBoolean);
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
