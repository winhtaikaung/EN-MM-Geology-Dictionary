package com.rangon.en_mmgeologydictionary.presentation;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCacheImpl;
import com.rangon.en_mmgeologydictionary.data.repository.ApiConfigDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.WordsDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.ApiConfigDataStoreFactory;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.WordDataStoreFactory;
import com.rangon.en_mmgeologydictionary.data.service.WordDAL;
import com.rangon.en_mmgeologydictionary.domain.executor.impl.ThreadExecutor;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.base.BaseActivity;
import com.rangon.en_mmgeologydictionary.presentation.presenters.MainScreenPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.impl.MainScreenPresenterImpl;
import com.rangon.en_mmgeologydictionary.presentation.ui.adapters.ViewPagerAdapter;
import com.rangon.en_mmgeologydictionary.threading.MainThreadImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainScreenPresenter.View {
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private MainScreenPresenter mMainScreenPresenter;
    private WordDataStoreFactory mWordDataStoreFactory;
    private WordsDataRepository mWordDataRepository;

    private ApiConfigDataStoreFactory mApiConfigDataStoreFactory;
    private ApiConfigDataRepository mApiConfigDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ButterKnife.bind(this);

        mApiConfigDataStoreFactory = new ApiConfigDataStoreFactory();
        mWordDataStoreFactory = new WordDataStoreFactory(new AppDataCacheImpl(this));

        mWordDataRepository = new WordsDataRepository(mWordDataStoreFactory, new WordDAL(this));
        mApiConfigDataRepository = new ApiConfigDataRepository(mApiConfigDataStoreFactory);

        mMainScreenPresenter = new MainScreenPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                this, mWordDataRepository, mApiConfigDataRepository);
        if (!new AppDataCacheImpl(this).isCached()) {
            mMainScreenPresenter.loadInitialData(1, 100);
        }

        mMainScreenPresenter.init();


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void onLoadInitialData(boolean isLoaded) {

    }

    @Override
    public void onWordListLoaded(List<Word> wordList) {

    }

    @Override
    public void onInit() {
        String[] titles = {"Search", "Bookmarks", "Settings"};
        int[] tabIcons = {
                R.drawable.ic_action_search,
                R.drawable.ic_action_bookmark,
                R.drawable.ic_action_setting
        };

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabIcons.length; i++) {
            View iconView = getLayoutInflater().inflate(R.layout.custom_tab, null);
            iconView.findViewById(R.id.icon).setBackgroundResource(tabIcons[i]);
            tabLayout.getTabAt(i).setCustomView(iconView);
            TextView tvTab = (TextView) tabLayout.getTabAt(i).getCustomView().findViewById(R.id.tvTab);
            tvTab.setText(titles[i]);
        }

        viewPager.setCurrentItem(0);

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
}
