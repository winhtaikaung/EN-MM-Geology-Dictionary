package domain.repository;

import domain.model.Word;
import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 17/7/17.
 */

public interface WordRepository {
    Observable<Word> getWord(String id);

    Observable<Word> getLikelyWord(String searchkeyword);

    Observable<Word> getWordList(String page,String size);
}
