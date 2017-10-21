package com.rangon.en_mmgeologydictionary.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.data.DAL.WordDAL;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCacheImpl;
import com.rangon.en_mmgeologydictionary.data.repository.ApiConfigDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.WordsDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.ApiConfigDataStoreFactory;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.WordDataStoreFactory;
import com.rangon.en_mmgeologydictionary.domain.executor.impl.ThreadExecutor;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.base.BaseActivity;
import com.rangon.en_mmgeologydictionary.presentation.broadcastReceivers.DataFetchServiceReceiver;
import com.rangon.en_mmgeologydictionary.presentation.presenters.MainScreenPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.impl.MainScreenPresenterImpl;
import com.rangon.en_mmgeologydictionary.presentation.service.DataFetchService;
import com.rangon.en_mmgeologydictionary.presentation.ui.adapters.ViewPagerAdapter;
import com.rangon.en_mmgeologydictionary.threading.MainThreadImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainScreenPresenter.View, DataFetchServiceReceiver.Receiver {
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private DataFetchServiceReceiver mReceiver;

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
            mReceiver = new DataFetchServiceReceiver(new Handler());
            mReceiver.setReceiver(this);
            Intent intent = new Intent(Intent.ACTION_SYNC, null, this, DataFetchService.class);
            intent.putExtra("receiver", mReceiver);
            startService(intent);

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

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case DataFetchService.STATUS_RUNNING:

                Toast.makeText(this, "STATUS_RUNNING", Toast.LENGTH_SHORT).show();
                break;
            case DataFetchService.STATUS_FINISHED:
                /* Hide progress & extract result from bundle */
                setProgressBarIndeterminateVisibility(false);

                String[] results = resultData.getStringArray("result");

                /* Update ListView with result */
//                arrayAdapter = new ArrayAdapter(MyActivity.this, android.R.layout.simple_list_item_2, results);
//                listView.setAdapter(arrayAdapter);
                Toast.makeText(this, "STATUS_FINISHED", Toast.LENGTH_SHORT).show();
                break;
            case DataFetchService.STATUS_ERROR:
                /* Handle the error */
//                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(this, "STATUS_ERROR", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
