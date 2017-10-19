package com.rangon.en_mmgeologydictionary.domain.interactors;

import com.rangon.en_mmgeologydictionary.domain.interactors.base.Interactor;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 18/10/17.
 */

public interface UpdateRecentWordInteractor extends Interactor {
    interface Callback {
        void onUpdateRecentWord(Observable<Boolean> wordUpdatedStatus);
    }
}
