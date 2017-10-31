package com.rangon.en_mmgeologydictionary.presentation.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.rangon.en_mmgeologydictionary.MainApplication;
import com.rangon.en_mmgeologydictionary.dagger.component.AppComponent;

/**
 * Created by winhtaikaung on 31/10/17.
 */

public abstract class BaseFragment extends Fragment {

    public abstract void injectDependencies(AppComponent component);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(MainApplication.getInstance().getApplicationComponent());
    }
}
