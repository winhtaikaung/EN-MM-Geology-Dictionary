package com.rangon.en_mmgeologydictionary.presentation.presenters;

import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.view.BaseView;

import java.util.List;

/**
 * Created by winhtaikaung on 10/10/17.
 */

public interface SearchScreenPresenter {
    void loadInitialData();

    void searchTextEnters(String searchText);

    void loadLikelyWordList(String searchText);

    interface View extends BaseView {
        void onLoadInitialData();

        void onLikelyWordListLoaded(List<Word> wordList);

        void onSearchTextReceived(String searchText);
    }
}
