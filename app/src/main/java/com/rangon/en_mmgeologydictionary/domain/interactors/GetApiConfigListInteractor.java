package com.rangon.en_mmgeologydictionary.domain.interactors;

import com.rangon.en_mmgeologydictionary.domain.interactors.base.Interactor;
import com.rangon.en_mmgeologydictionary.model.configrestmodels.ApiConfig;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by win on 9/16/17.
 */

public interface GetApiConfigListInteractor extends Interactor {
    interface Callback {
        void onApiConfigListReceived(Observable<List<ApiConfig>> apiconfigList);
    }
}
