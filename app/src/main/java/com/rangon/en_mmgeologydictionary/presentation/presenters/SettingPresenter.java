package com.rangon.en_mmgeologydictionary.presentation.presenters;

import com.rangon.en_mmgeologydictionary.presentation.view.BaseView;

/**
 * Created by winhtaikaung on 15/10/17.
 */

public interface SettingPresenter {
    void loadInitialData();

    void resetAppData();

    void fontSelected();
    interface View extends BaseView {
        void onLoadInitialData();

        void onAppDataReset();

        void onFontSelected();

    }
}
