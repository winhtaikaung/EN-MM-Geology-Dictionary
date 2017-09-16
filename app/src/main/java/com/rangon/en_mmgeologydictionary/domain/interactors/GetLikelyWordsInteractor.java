package com.rangon.en_mmgeologydictionary.domain.interactors;

import java.util.List;

import com.rangon.en_mmgeologydictionary.domain.interactors.base.Interactor;
import io.reactivex.Observable;
import com.rangon.en_mmgeologydictionary.model.Word;

/**
 * Created by win on 7/29/17.
 */

public interface GetLikelyWordsInteractor extends Interactor {

    interface Callback {
        void onLikelyWordsRetrieved(Observable<List<Word>> likelyWordsListObservable);
    }
}
