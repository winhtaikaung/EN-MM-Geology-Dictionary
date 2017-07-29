package domain.interactors;

import domain.interactors.base.Interactor;
import io.reactivex.Observable;
import model.Word;

/**
 * Created by win on 7/29/17.
 */

public interface GetWordInteractor extends Interactor {
    interface Callback {
        void onWordRetrieved(Observable<Word> wordObservable);
    }
}
