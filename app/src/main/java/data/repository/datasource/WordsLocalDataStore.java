package data.repository.datasource;

import java.util.List;

import data.service.WordDAC;
import io.reactivex.Observable;
import model.Word;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public class WordsLocalDataStore implements WordsDataStore {
    private WordDAC wordDac;
    public WordsLocalDataStore() {
        wordDac = new WordDAC();
    }


    @Override
    public Observable<Word> getWord(String word) {
        return Observable.just(wordDac.getByWord(word));
    }

    @Override
    public Observable<List<Word>> getLikelyWord(String searchKeyword) {
        return Observable.just(wordDac.getLikelyWords(searchKeyword));
    }

    @Override
    public Observable<List<Word>> getWordList(String page, String size) {
        return null;
    }
}
