package com.rangon.en_mmgeologydictionary.dagger;


import android.content.Context;

import com.rangon.en_mmgeologydictionary.dagger.scope.FragmentScope;
import com.rangon.en_mmgeologydictionary.data.repository.WordsDataRepository;
import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.presentation.presenters.SearchScreenPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.impl.SearchScreenPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by winhtaikaung on 31/10/17.
 */
@Module
public class SearchFragmentModule {

    private Context mContext;
    private SearchScreenPresenter.View mView;

    public SearchFragmentModule(SearchScreenPresenter.View view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    @FragmentScope
    @Provides
    public SearchScreenPresenter.View provideSearchScreenPresenterView() {
        return mView;
    }

    @FragmentScope
    @Provides
    SearchScreenPresenterImpl provideSearchScreenPresenterImpl(Executor executor, MainThread mainThread, SearchScreenPresenter.View view,
                                                               WordsDataRepository repository) {
        return new SearchScreenPresenterImpl(executor, mainThread, view, repository);
    }


    @FragmentScope
    @Provides
    public SearchScreenPresenter provideSearchScreenPresenter(SearchScreenPresenterImpl presenter) {
        return presenter;
    }


}
