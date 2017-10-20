package com.rangon.en_mmgeologydictionary.presentation.presenters;

import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.view.BaseView;

import java.util.List;

/**
 * Created by winhtaikaung on 19/10/17.
 */

public interface BookmarkViewPresenter {
    void loadInitialData();

    void loadBookmarksData(int limit, int page);

    interface View extends BaseView {
        void onInitialDataLoaded();

        void onBookMarkDataLoaded(List<Word> bookMarkWordList);
    }
}
