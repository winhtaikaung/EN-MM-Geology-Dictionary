package com.rangon.en_mmgeologydictionary.domain.interactors;

import com.rangon.en_mmgeologydictionary.domain.interactors.base.Interactor;
import io.reactivex.Observable;
import com.rangon.en_mmgeologydictionary.model.Word;

/**
 * Created by win on 7/29/17.
 */

public interface GetWordInteractor extends Interactor {
    interface Callback {
        void onWordRetrieved(Observable<Word> wordObservable);
    }
}
