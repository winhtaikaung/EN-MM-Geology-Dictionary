package com.rangon.en_mmgeologydictionary.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.ui.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rangon.en_mmgeologydictionary.presentation.ui.base.EndlessRecyclerViewAdapter.TYPE_PENDING;

/**
 * Created by winhtaikaung on 11/10/17.
 */

public class AdapterWordList extends BaseAdapter<BaseAdapter.BaseViewHolder> {

    Context mContext;
    List<Word> mWordList;
    boolean mIsHorizontal = false;

    private int HORIZONTAL_VIEW = 111;
    private int VERTICAL_VIEW = 000;

    public AdapterWordList() {
        mWordList = new ArrayList<>();
    }

    public void setWordList(List<Word> wordList, boolean isHorizontal) {
        this.mWordList = wordList;
        this.mIsHorizontal = isHorizontal;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if (viewType == VERTICAL_VIEW) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_word_list, parent, false);
            return new WordListViewHolder(view, this);
        } else if (viewType == HORIZONTAL_VIEW) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_hor_related_word, parent, false);
            return new WordListHorizontalViewHolder(view, this);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_word_list, parent, false);
            return new WordListViewHolder(view, this);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) != TYPE_PENDING) {
            if (getItemViewType(position) == VERTICAL_VIEW) {
                WordListViewHolder vh = (WordListViewHolder) holder;
                if (vh != null) {
                    if (mWordList.get(position) != null) {
                        Word wordItem = mWordList.get(position);
                        vh.tvWord.setTag(wordItem.getId());
                        vh.tvWord.setText(wordItem.getWord());
                        vh.tvDefinition.setText(wordItem.getMeaningUni());
                    }
                }
            } else if (getItemViewType(position) == HORIZONTAL_VIEW) {
                WordListHorizontalViewHolder vh = (WordListHorizontalViewHolder) holder;
                if (vh != null) {
                    if (mWordList.get(position) != null) {
                        Word wordItem = mWordList.get(position);
                        vh.tvWord.setTag(wordItem.getId());
                        vh.tvWord.setText(wordItem.getWord());
                        if (wordItem.getType().isEmpty()) {
                            vh.tvWordType.setVisibility(View.GONE);
                        } else {
                            vh.tvWordType.setVisibility(View.VISIBLE);
                            vh.tvWordType.setText(wordItem.getType());
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mWordList != null ? mWordList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mIsHorizontal) {
            return HORIZONTAL_VIEW;
        } else {
            return VERTICAL_VIEW;
        }
    }

    class WordListViewHolder extends BaseAdapter.BaseViewHolder {

        @BindView(R.id.tv_word)
        TextView tvWord;

        @BindView(R.id.tv_definition)
        TextView tvDefinition;

        public WordListViewHolder(View itemView, AdapterWordList adapterWordList) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            mAdapter = adapterWordList;
        }
    }

    class WordListHorizontalViewHolder extends BaseAdapter.BaseViewHolder {

        @BindView(R.id.tv_word)
        TextView tvWord;

        @BindView(R.id.tv_word_type)
        TextView tvWordType;

        public WordListHorizontalViewHolder(View itemView, AdapterWordList adapterWordList) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            mAdapter = adapterWordList;
        }
    }
}
