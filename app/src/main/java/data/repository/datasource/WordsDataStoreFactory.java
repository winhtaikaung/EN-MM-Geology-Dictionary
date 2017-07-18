package data.repository.datasource;

import java.util.List;

import io.reactivex.Observable;
import model.Word;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public class WordsDataStoreFactory implements WordsDataStore {
    public WordsDataStoreFactory() {

    }


    @Override
    public Observable<Word> getWord(String word) {
        return null;
    }

    @Override
    public Observable<List<Word>> getLikelyWord(String searchKeyword) {
        return null;
    }

    @Override
    public Observable<List<Word>> getWordList(String page, String size) {
        return null;
    }
}
