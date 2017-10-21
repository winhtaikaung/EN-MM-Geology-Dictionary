package com.rangon.en_mmgeologydictionary.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.data.DAL.WordDAL;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCacheImpl;
import com.rangon.en_mmgeologydictionary.data.repository.WordsDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.WordDataStoreFactory;
import com.rangon.en_mmgeologydictionary.domain.executor.impl.ThreadExecutor;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.presenters.BookmarkViewPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.impl.BookmarkViewPresenterImpl;
import com.rangon.en_mmgeologydictionary.presentation.ui.activities.WordDetailActivity;
import com.rangon.en_mmgeologydictionary.presentation.ui.adapters.AdapterWordList;
import com.rangon.en_mmgeologydictionary.presentation.ui.base.EndlessRecyclerViewAdapter;
import com.rangon.en_mmgeologydictionary.threading.MainThreadImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by winhtaikaung on 8/10/17.
 */

public class BookMarkFragment extends Fragment implements BookmarkViewPresenter.View, AdapterView.OnItemClickListener {

    @BindView(R.id.rv_word_list)
    RecyclerView mRvWordListView;

    @BindView(R.id.tvError)
    TextView mTvError;

    private BookmarkViewPresenter mBookmarkScreenPresenter;
    private WordDataStoreFactory mWordDataStoreFactory;
    private WordsDataRepository mWordsDataRepository;

    private AdapterWordList mWordListAdapter;
    private EndlessRecyclerViewAdapter mEndlessRecyclerViewAdapter;
    private int mCounter = 1;
    private List<Word> mWordList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bookmark, container, false);
        ButterKnife.bind(this, v);
        mWordDataStoreFactory = new WordDataStoreFactory(new AppDataCacheImpl(this.getContext()));
        mWordsDataRepository = new WordsDataRepository(mWordDataStoreFactory, new WordDAL(this.getContext()));
        mBookmarkScreenPresenter = new BookmarkViewPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                this, mWordsDataRepository);
        mBookmarkScreenPresenter.loadInitialData();
        return v;
    }

    @Override
    public void onInitialDataLoaded() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mWordListAdapter = new AdapterWordList();
        mWordListAdapter.setOnItemClickListener(this);
        mRvWordListView.setLayoutManager(mLayoutManager);
        mEndlessRecyclerViewAdapter = new EndlessRecyclerViewAdapter(this.getActivity(), mWordListAdapter, new EndlessRecyclerViewAdapter.RequestToLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mCounter == 1) {
                    mBookmarkScreenPresenter.loadBookmarksData(10, 1);
                } else {
                    mBookmarkScreenPresenter.loadBookmarksData(10, mCounter);
                }
            }
        });
        mRvWordListView.setAdapter(mEndlessRecyclerViewAdapter);
    }

    @Override
    public void onBookMarkDataLoaded(List<Word> bookMarkWordList) {
        if (mCounter == 1) {

            if (bookMarkWordList.size() > 0) {

                if (mCounter == 1) {
                    mWordList = bookMarkWordList;
                } else {

                    mWordList.addAll(bookMarkWordList);

                }
                mWordListAdapter.setWordList(mWordList);
                mEndlessRecyclerViewAdapter.onDataReady(true);
                mCounter++;

            } else {
                showError(this.getResources().getString(R.string.no_word_found));
                mEndlessRecyclerViewAdapter.onDataReady(false);
            }
        } else {
            hideError("");
            if (bookMarkWordList.size() > 0) {

                if (mCounter == 1) {
                    mWordList = bookMarkWordList;
                } else {

                    mWordList.addAll(bookMarkWordList);

                }
                mWordListAdapter.setWordList(mWordList);
                mEndlessRecyclerViewAdapter.onDataReady(true);
                mCounter++;

            } else {
                mEndlessRecyclerViewAdapter.onDataReady(false);
            }
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        mRvWordListView.setVisibility(View.GONE);
        mTvError.setVisibility(View.VISIBLE);
        mTvError.setText(message);
    }

    @Override
    public void hideError(String message) {
        mRvWordListView.setVisibility(View.VISIBLE);
        mTvError.setVisibility(View.GONE);
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
