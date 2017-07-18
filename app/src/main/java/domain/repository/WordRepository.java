package domain.repository;

import java.util.List;

import model.Word;
import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 17/7/17.
 */

public interface WordRepository {
    Observable<Word> getWord(String word);

    Observable<List<Word>> getLikelyWord(String searchKeyword);

    Observable<List<Word>> getWordList(String page,String size);
}
