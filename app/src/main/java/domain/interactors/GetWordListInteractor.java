package domain.interactors;

import java.util.List;

import domain.interactors.base.Interactor;
import io.reactivex.Observable;
import model.Word;

/**
 * Created by win on 9/10/17.
 */

public interface GetWordListInteractor extends Interactor {
    interface Callback {
        void onWordListRetrieved(Observable<List<Word>> wordListObservable);
    }
}
