package com.rangon.en_mmgeologydictionary.presentation.ui.viewholders.impl;

import com.rangon.en_mmgeologydictionary.model.SettingItem;
import com.rangon.en_mmgeologydictionary.presentation.ui.adapters.AdapterSettingList;
import com.rangon.en_mmgeologydictionary.presentation.ui.viewholders.SettingListItemViewPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winhtaikaung on 6/11/17.
 */

public class SettingListItemViewPresenterImpl implements SettingListItemViewPresenter {
    private List<SettingItem> mSettingItemList = new ArrayList<>();


    public SettingListItemViewPresenterImpl(List<SettingItem> items) {
        this.mSettingItemList = items;

    }


    @Override
    public void onBindSettingListViewAtPosition(int position, AdapterSettingList.SettingsListItemHolder view) {
        SettingItem item = mSettingItemList.get(position);
        view.setTitleText(item.getSettingTitle());
        view.setIconResource(item.getIconResId());
    }

    @Override
    public int getSettingItemRowsCount() {
        return (mSettingItemList.size() == 0) ? 0 : mSettingItemList.size();
    }
}
