package com.rangon.en_mmgeologydictionary.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCacheImpl;
import com.rangon.en_mmgeologydictionary.data.repository.WordsDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.WordDataStoreFactory;
import com.rangon.en_mmgeologydictionary.data.service.WordDAL;
import com.rangon.en_mmgeologydictionary.domain.executor.impl.ThreadExecutor;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.presenters.WordDetailPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.impl.WordDetailPresenterImpl;
import com.rangon.en_mmgeologydictionary.threading.MainThreadImpl;


public class WordDetailActivity extends AppCompatActivity  implements WordDetailPresenter.View{


    private WordDetailPresenter mWordDetailPresenter;
    private WordDataStoreFactory mWordDataStoreFactory;
    private WordsDataRepository mWordsDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
        mWordDataStoreFactory = new WordDataStoreFactory(new AppDataCacheImpl(this));
        mWordsDataRepository = new WordsDataRepository(mWordDataStoreFactory, new WordDAL(this));
        mWordDetailPresenter = new WordDetailPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                this, mWordsDataRepository);

        mWordDetailPresenter.getWordDetail("auric","ea5ca197-b871-4313-bb21-b7b518b965ec");
    }

    @Override
    public void onWordRetrieved(Word word) {
        word.toString();
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
