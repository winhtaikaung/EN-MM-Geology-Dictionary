package com.rangon.en_mmgeologydictionary.presentation.presenters;

import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.view.BaseView;

import java.util.List;

/**
 * Created by winhtaikaung on 29/7/17.
 */

public interface WordDetailPresenter extends BaseView {
    void initialize();

    void getWordDetail(String word, String id);

    void getRelatedWord(String word);

    void updateRecentWord(String word, String id);

    interface View extends BaseView {
        void onInit();

        void onWordRetrieved(Word word);

        void onRelatedWordRetrieved(List<Word> relatedWordList);

        void onWordRecentUpdated(Boolean status);
    }
}
