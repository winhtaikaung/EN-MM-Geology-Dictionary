package com.rangon.en_mmgeologydictionary.presentation.presenters.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.presentation.presenters.AbstractPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.SettingPresenter;

/**
 * Created by winhtaikaung on 15/10/17.
 */

public class SettingPresenterImpl extends AbstractPresenter implements SettingPresenter {

    public SettingPresenterImpl(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
    }

    @Override
    public void loadInitialData() {

    }

    @Override
    public void resetAppData() {

    }

    @Override
    public void fontSelected() {

    }
}
