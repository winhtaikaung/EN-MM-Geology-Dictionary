package com.rangon.en_mmgeologydictionary.domain.interactors;

import com.rangon.en_mmgeologydictionary.domain.interactors.base.Interactor;
import com.rangon.en_mmgeologydictionary.model.Word;

import io.reactivex.Observable;

/**
 * Created by win on 7/29/17.
 */

public interface GetWordInteractor extends Interactor {
    interface Callback {
        void onWordRetrieved(Observable<Word> wordObservable);
    }
}
