package data.repository;

import java.util.List;

import data.repository.datasource.WordDataStoreFactory;
import domain.repository.WordRepository;
import io.reactivex.Observable;
import model.Word;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public class WordsDataRepository implements WordRepository {

    private final WordDataStoreFactory mWordDataStoreFactory;

    /**
     * @param wordsDataStoreFactory
     */
    public WordsDataRepository(WordDataStoreFactory wordsDataStoreFactory) {
        this.mWordDataStoreFactory = wordsDataStoreFactory;
    }

    /**
     * @param word
     * @return
     */
    @Override
    public Observable<Word> getWord(String word) {
        return mWordDataStoreFactory.create().getWord(word).map(word1 -> word1);
    }

    /**
     * @param searchKeyword
     * @return
     */
    @Override
    public Observable<List<Word>> getLikelyWord(String searchKeyword) {
        return mWordDataStoreFactory.create().getLikelyWord(searchKeyword).map(words -> words);
    }

    /**
     * @param page
     * @param size
     * @return
     */
    @Override
    public Observable<List<Word>> getWordList(String wordIndex, int page, int size) {
        return mWordDataStoreFactory.create().getWordList(wordIndex, page, size).map(words -> words);
    }
}
