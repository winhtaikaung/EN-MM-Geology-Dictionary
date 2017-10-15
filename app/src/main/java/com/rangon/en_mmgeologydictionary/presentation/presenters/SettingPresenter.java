package com.rangon.en_mmgeologydictionary.presentation.presenters;

import com.rangon.en_mmgeologydictionary.data.cache.AppDataCache;
import com.rangon.en_mmgeologydictionary.model.SettingItem;
import com.rangon.en_mmgeologydictionary.presentation.view.BaseView;

import java.util.List;

/**
 * Created by winhtaikaung on 15/10/17.
 */

public interface SettingPresenter {
    void loadInitialData(AppDataCache appDataCache);

    void resetAppData();

    void fontSelected();

    interface View extends BaseView {
        void onLoadInitialData(List<SettingItem> settingItems);

        void onAppDataReset(boolean b);

        void onFontSelected();

    }
}
