package com.rangon.en_mmgeologydictionary.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCacheImpl;
import com.rangon.en_mmgeologydictionary.data.repository.WordsDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.WordDataStoreFactory;
import com.rangon.en_mmgeologydictionary.data.service.WordDAL;
import com.rangon.en_mmgeologydictionary.domain.executor.impl.ThreadExecutor;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.base.BaseActivity;
import com.rangon.en_mmgeologydictionary.presentation.presenters.WordDetailPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.impl.WordDetailPresenterImpl;
import com.rangon.en_mmgeologydictionary.presentation.ui.adapters.AdapterWordDetail;
import com.rangon.en_mmgeologydictionary.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WordDetailActivity extends BaseActivity implements WordDetailPresenter.View, AdapterView.OnItemClickListener {

    @BindView(R.id.rv_word_detail)
    RecyclerView rvWordDetail;

    private WordDetailPresenter mWordDetailPresenter;
    private WordDataStoreFactory mWordDataStoreFactory;
    private WordsDataRepository mWordsDataRepository;

    private AdapterWordDetail mWordDetailAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mWordDataStoreFactory = new WordDataStoreFactory(new AppDataCacheImpl(this));
        mWordsDataRepository = new WordsDataRepository(mWordDataStoreFactory, new WordDAL(this));
        mWordDetailPresenter = new WordDetailPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                this, mWordsDataRepository);

        mWordDetailPresenter.initialize();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_word_detail;
    }

    @Override
    public void onInit() {


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mWordDetailAdatper = new AdapterWordDetail();
        mWordDetailAdatper.setOnItemClickListener(this);
        rvWordDetail.setLayoutManager(mLayoutManager);
        rvWordDetail.setAdapter(mWordDetailAdatper);
        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        String word = intent.getExtras().getString("word");
        this.getSupportActionBar().setTitle(word);
        mWordDetailPresenter.getWordDetail(word, id);

    }


    @Override
    public void onWordRetrieved(Word word) {
        mWordDetailAdatper.setWord(word);
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
