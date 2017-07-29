package domain.interactors;

import java.util.List;

import domain.interactors.base.Interactor;
import io.reactivex.Observable;
import model.Word;

/**
 * Created by win on 7/29/17.
 */

public interface GetLikelyWordsInteractor extends Interactor {

    interface Callback {
        void onLikelyWordsRetrieved(Observable<List<Word>> likelyWordsListObservable);
    }
}
