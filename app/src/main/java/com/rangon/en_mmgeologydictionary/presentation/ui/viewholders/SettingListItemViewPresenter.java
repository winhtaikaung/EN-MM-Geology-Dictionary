package com.rangon.en_mmgeologydictionary.presentation.ui.viewholders;

import com.rangon.en_mmgeologydictionary.presentation.ui.adapters.AdapterSettingList;

/**
 * Created by winhtaikaung on 6/11/17.
 */

public interface SettingListItemViewPresenter {

    void onBindSettingListViewAtPosition(int position, AdapterSettingList.SettingsListItemHolder view);

    int getSettingItemRowsCount();

    interface SettingListItemView {

        void setTitleText(String titleText);

        void setIconResource(int resourceID);
    }
}
