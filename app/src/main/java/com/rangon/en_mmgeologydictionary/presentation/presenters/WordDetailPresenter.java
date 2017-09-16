package com.rangon.en_mmgeologydictionary.presentation.presenters;

import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.view.BaseView;

/**
 * Created by winhtaikaung on 29/7/17.
 */

public interface WordDetailPresenter extends BaseView {

    void getWordDetail(String word);

    interface View extends BaseView {
        void onWordRetrieved(Word word);
    }
}
