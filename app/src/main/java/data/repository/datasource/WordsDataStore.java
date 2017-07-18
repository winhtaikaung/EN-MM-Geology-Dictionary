package data.repository.datasource;

import java.util.List;

import io.reactivex.Observable;
import model.Word;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public interface WordsDataStore {
    Observable<Word> getWord(String word);

    Observable<List<Word>> getLikelyWord(String searchKeyword);

    Observable<List<Word>> getWordList(String page, String size);
}
