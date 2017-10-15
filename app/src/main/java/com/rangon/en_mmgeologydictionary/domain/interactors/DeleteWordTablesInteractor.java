package com.rangon.en_mmgeologydictionary.domain.interactors;

import com.rangon.en_mmgeologydictionary.domain.interactors.base.Interactor;

import io.reactivex.Observable;


/**
 * Created by winhtaikaung on 15/10/17.
 */

public interface DeleteWordTablesInteractor extends Interactor{
    interface Callback{
        void onWordTableDeleted(Observable<Boolean> wordDeletedStatus);
    }
}
