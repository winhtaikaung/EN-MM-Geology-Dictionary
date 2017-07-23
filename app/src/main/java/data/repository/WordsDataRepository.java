package data.repository;

import java.util.List;

import data.repository.datasource.WordDataStoreFactory;
import domain.repository.WordRepository;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
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
        return mWordDataStoreFactory.create().getWord(word).map(new Function<Word, Word>() {
            @Override
            public Word apply(Word word) throws Exception {
                return word;
            }
        });
    }

    /**
     * @param searchKeyword
     * @return
     */
    @Override
    public Observable<List<Word>> getLikelyWord(String searchKeyword) {
        return mWordDataStoreFactory.create().getLikelyWord(searchKeyword).map(new Function<List<Word>, List<Word>>() {
            @Override
            public List<Word> apply(List<Word> words) throws Exception {
                return words;
            }
        });
    }

    /**
     * @param page
     * @param size
     * @return
     */
    @Override
    public Observable<List<Word>> getWordList(String page, String size) {
        return mWordDataStoreFactory.create().getWordList(page, size).map(new Function<List<Word>, List<Word>>() {
            @Override
            public List<Word> apply(List<Word> words) throws Exception {
                return words;
            }
        });
    }
}
