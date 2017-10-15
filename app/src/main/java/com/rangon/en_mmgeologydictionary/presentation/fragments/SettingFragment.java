package com.rangon.en_mmgeologydictionary.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCacheImpl;
import com.rangon.en_mmgeologydictionary.data.repository.WordsDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.WordDataStoreFactory;
import com.rangon.en_mmgeologydictionary.data.service.WordDAL;
import com.rangon.en_mmgeologydictionary.domain.executor.impl.ThreadExecutor;
import com.rangon.en_mmgeologydictionary.model.SettingItem;
import com.rangon.en_mmgeologydictionary.presentation.presenters.SettingPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.impl.SettingPresenterImpl;
import com.rangon.en_mmgeologydictionary.presentation.ui.adapters.AdapterSettingList;
import com.rangon.en_mmgeologydictionary.threading.MainThreadImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by winhtaikaung on 8/10/17.
 */

public class SettingFragment extends Fragment implements SettingPresenter.View, AdapterView.OnItemClickListener {

    @BindView(R.id.rv_setting_List)
    RecyclerView rvSettingList;

    private SettingPresenter mSettingPresenter;
    private WordDataStoreFactory mWordDataStoreFactory;
    private WordsDataRepository mWordsDataRepository;

    AdapterSettingList mSettingListAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, v);
        mWordDataStoreFactory = new WordDataStoreFactory(new AppDataCacheImpl(this.getContext()));
        mWordsDataRepository = new WordsDataRepository(mWordDataStoreFactory, new WordDAL(this.getContext()));
        mSettingPresenter = new SettingPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this, mWordsDataRepository);
        mSettingPresenter.loadInitialData();
        return v;
    }

    @Override
    public void onLoadInitialData(List<SettingItem> settingItemList) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mSettingListAdapter = new AdapterSettingList();
        mSettingListAdapter.setOnItemClickListener(this);
        rvSettingList.setLayoutManager(mLayoutManager);
        rvSettingList.setAdapter(mSettingListAdapter);

        mSettingListAdapter.setSettingList(settingItemList);
    }

    @Override
    public void onAppDataReset(boolean b) {

    }

    @Override
    public void onFontSelected() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void hideError(String message) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
