package com.rangon.en_mmgeologydictionary.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.data.DAL.WordDAL;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCacheImpl;
import com.rangon.en_mmgeologydictionary.data.repository.WordsDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.WordDataStoreFactory;
import com.rangon.en_mmgeologydictionary.domain.executor.impl.ThreadExecutor;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.presenters.SearchScreenPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.impl.SearchScreenPresenterImpl;
import com.rangon.en_mmgeologydictionary.presentation.ui.activities.WordDetailActivity;
import com.rangon.en_mmgeologydictionary.presentation.ui.adapters.AdapterWordList;
import com.rangon.en_mmgeologydictionary.presentation.ui.base.EndlessRecyclerViewAdapter;
import com.rangon.en_mmgeologydictionary.threading.MainThreadImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by winhtaikaung on 8/10/17.
 */

public class SearchFragment extends Fragment implements SearchScreenPresenter.View, AdapterView.OnItemClickListener {

    @BindView(R.id.rv_word_list)
    RecyclerView mRvWordListView;

    @BindView(R.id.tvSearch)
    AppCompatEditText mTvSearchEditText;

    @BindView(R.id.list_layout)
    LinearLayout mListLayout;

    @BindView(R.id.tvError)
    TextView tvErrorText;

    private SearchScreenPresenter mSearchScreenPresenter;
    private WordDataStoreFactory mWordDataStoreFactory;
    private WordsDataRepository mWordsDataRepository;

    private AdapterWordList mWordListAdapter;
    private EndlessRecyclerViewAdapter mEndlessRecyclerViewAdapter;
    private int mCounter = 1;
    private List<Word> mWordList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, v);

        mWordDataStoreFactory = new WordDataStoreFactory(new AppDataCacheImpl(this.getContext()));
        mWordsDataRepository = new WordsDataRepository(mWordDataStoreFactory, new WordDAL(this.getContext()));
        mSearchScreenPresenter = new SearchScreenPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                this, mWordsDataRepository);
        mSearchScreenPresenter.loadInitialData();
        showError("No word found");
        return v;
    }


    @Override
    public void onLoadInitialData() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mWordListAdapter = new AdapterWordList();
        mWordListAdapter.setOnItemClickListener(this);
        mRvWordListView.setLayoutManager(mLayoutManager);


        RxTextView.textChanges(mTvSearchEditText)
                .skip(1)
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(@NonNull CharSequence charSequence) throws Exception {
                        return charSequence.toString();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (s.equalsIgnoreCase("")) {
                    showError("No Word Found");
                } else {
                    hideError("");
                    mSearchScreenPresenter.searchTextEnters(s);
                }
            }
        });

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        mListLayout.setVisibility(View.GONE);
        tvErrorText.setVisibility(View.VISIBLE);
        tvErrorText.setText(message);
    }

    @Override
    public void hideError(String message) {
        mListLayout.setVisibility(View.VISIBLE);
        tvErrorText.setVisibility(View.GONE);

    }


    @Override
    public void onLikelyWordListLoaded(List<Word> wordList) {

        Log.e("QUERY_SIZE", String.valueOf(wordList.size()));
        if (wordList.size() > 0) {
            if (mCounter == 1) {
                mWordList = wordList;
            } else {

                mWordList.addAll(wordList);

            }
            mWordListAdapter.setWordList(mWordList);
            mEndlessRecyclerViewAdapter.onDataReady(true);
            mCounter++;


        } else {
            Log.e("ON_DATA_READY", "FALSE");
            mEndlessRecyclerViewAdapter.onDataReady(false);
        }

    }

    @Override
    public void onSearchTextReceived(String searchText) {

        mWordList = new ArrayList<>();
        mSearchScreenPresenter.loadLikelyWordList(searchText, 10, 1);

        mEndlessRecyclerViewAdapter = new EndlessRecyclerViewAdapter(this.getActivity(), mWordListAdapter, new EndlessRecyclerViewAdapter.RequestToLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mCounter == 1) {
                    mSearchScreenPresenter.loadLikelyWordList(searchText, 10, 1);
                } else {
                    mSearchScreenPresenter.loadLikelyWordList(searchText, 10, mCounter);
                }
            }
        });

        mRvWordListView.setAdapter(mEndlessRecyclerViewAdapter);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView tvword = (TextView) view.findViewById(R.id.tv_word);
        if (tvword != null) {

            Log.e("UUID+WORD", tvword.getTag().toString() + "--" + tvword.getText());
            Intent intent = new Intent(this.getActivity(), WordDetailActivity.class);
            intent.putExtra("id", tvword.getTag().toString());
            intent.putExtra("word", tvword.getText().toString());
            startActivity(intent);
        } else {
            //TODO invalid data state handling
            this.showError("Invalid Data please go to setting again");
        }

    }
}
