package com.rangon.en_mmgeologydictionary.dagger.component;

import com.rangon.en_mmgeologydictionary.dagger.SearchFragmentModule;
import com.rangon.en_mmgeologydictionary.dagger.scope.FragmentScope;
import com.rangon.en_mmgeologydictionary.presentation.fragments.SearchFragment;

import dagger.Subcomponent;

/**
 * Created by winhtaikaung on 31/10/17.
 */
@FragmentScope
@Subcomponent(modules = {
        SearchFragmentModule.class
})
public interface SearchFragmentComponent {

    void inject(SearchFragment fragment);
}
