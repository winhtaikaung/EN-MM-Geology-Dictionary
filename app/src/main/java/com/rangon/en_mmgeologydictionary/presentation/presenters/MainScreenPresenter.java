package com.rangon.en_mmgeologydictionary.presentation.presenters;

import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.view.BaseView;

import java.util.List;

/**
 * Created by win on 9/16/17.
 */

public interface MainScreenPresenter {

    void loadInitialData(int page, int limit);

    void loadWordList(int page, int limit);

    void init();

    interface View extends BaseView {
        void onLoadInitialData(boolean isLoaded);

        void onWordListLoaded(List<Word> wordList);

        void onInit();


    }
}
