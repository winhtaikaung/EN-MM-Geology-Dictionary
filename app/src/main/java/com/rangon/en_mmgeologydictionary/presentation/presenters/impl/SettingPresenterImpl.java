package com.rangon.en_mmgeologydictionary.presentation.presenters.impl;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.DeleteWordTablesInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.DeleteWordTablesInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.SettingItem;
import com.rangon.en_mmgeologydictionary.presentation.presenters.AbstractPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.SettingPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by winhtaikaung on 15/10/17.
 */

public class SettingPresenterImpl extends AbstractPresenter implements SettingPresenter, DeleteWordTablesInteractor.Callback {


    private SettingPresenter.View mView;
    private WordRepository mWordRepository;

    public SettingPresenterImpl(Executor executor, MainThread mainThread, View view, WordRepository repository) {
        super(executor, mainThread);
        this.mView = view;
        this.mWordRepository = repository;
    }

    @Override
    public void loadInitialData() {
        //load font data setting etc
        String[] mSettingListTitles = {"About Us", "Reset", "Font"};
        int[] mSettingListIcons = {R.drawable.ic_info, R.drawable.ic_reset, 999};
        List<SettingItem> settingItems = new ArrayList<>();
        for (int i = 0;i < mSettingListTitles.length; i++) {
            SettingItem item = new SettingItem().SettingListItem(mSettingListTitles[i],mSettingListIcons[i]);
            settingItems.add(item);
        }

        mView.onLoadInitialData(settingItems);
    }

    @Override
    public void resetAppData() {
        String[] tables = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        DeleteWordTablesInteractor deleteWordTablesInteractor = new DeleteWordTablesInteractorImpl(mExecutor, mMainThread, mWordRepository, tables, this);
        deleteWordTablesInteractor.execute();
    }

    @Override
    public void fontSelected() {

    }

    @Override
    public void onWordTableDeleted(Observable<Boolean> wordDeletedStatus) {
        wordDeletedStatus.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                mView.onAppDataReset(aBoolean);
            }
        });
    }
}
