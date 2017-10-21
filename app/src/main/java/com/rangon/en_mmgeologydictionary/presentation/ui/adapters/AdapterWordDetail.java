package com.rangon.en_mmgeologydictionary.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.ui.base.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rangon.en_mmgeologydictionary.presentation.base.EndlessRecyclerViewAdapter.TYPE_PENDING;


/**
 * Created by winhtaikaung on 15/10/17.
 */

public class AdapterWordDetail extends BaseAdapter<BaseAdapter.BaseViewHolder> {

    int WORD_HEADER_VIEW = 000;
    int WORD_DETAIL_VIEW = 111;
    int WORD_RELATED_VIEW = 222;

    Word mWord;
    List<Word> mWords;
    Context mContext;
    LinearLayoutManager mLayoutManager;
    AdapterWordList adapterWordList;

    public AdapterWordDetail() {
        mWord = new Word("PlaceHolder", "PlaceHolder", "PlaceHolder", "PlaceHolder", "PlaceHolder", false);
    }

    public void setWord(Word word) {
        mWord = word;
        notifyDataSetChanged();
    }

    public void setRelatedWords(List<Word> words) {
        mWords = words;
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapterWordList = new AdapterWordList();
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        if (viewType == WORD_HEADER_VIEW) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_header, parent, false);
            return new WordHeaderViewHolder(view, this);
        } else if (viewType == WORD_DETAIL_VIEW) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_definition, parent, false);
            return new WordDetailViewHolder(view, this);
        } else if (viewType == WORD_RELATED_VIEW) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_related_word_container, parent, false);
            return new RelatedWordViewHolder(view, this);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) != TYPE_PENDING) {
            if (getItemViewType(position) == WORD_HEADER_VIEW) {
                WordHeaderViewHolder vh = (WordHeaderViewHolder) holder;
                if (vh != null) {
                    if (mWord != null) {
                        Word wordItem = mWord;
                        vh.tvWord.setTag(wordItem.getId());
                        vh.tvWord.setText(wordItem.getWord());
                        if (TextUtils.isEmpty(wordItem.getType())) {
                            vh.tvWordType.setVisibility(View.GONE);
                        }
                        vh.tvWordType.setText(wordItem.getType());
                    }
                }
            } else if (getItemViewType(position) == WORD_DETAIL_VIEW) {
                WordDetailViewHolder vh = (WordDetailViewHolder) holder;
                if (vh != null) {
                    if (mWord != null) {
                        Word wordItem = mWord;
                        vh.tvDefinition.setText(wordItem.getMeaningUni());
                    }
                }
            } else if (getItemViewType(position) == WORD_RELATED_VIEW) {
                RelatedWordViewHolder vh = (RelatedWordViewHolder) holder;
                if (vh != null) {
                    if (mWords != null) {

                        adapterWordList.setWordList(mWords, true);
                        vh.rvWordList.setLayoutManager(mLayoutManager);
                        vh.rvWordList.setAdapter(adapterWordList);
                    }
                }
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return WORD_HEADER_VIEW;
        } else if (position == 1) {
            return WORD_DETAIL_VIEW;
        } else if (position == 2) {
            return WORD_RELATED_VIEW;
        } else {
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class WordHeaderViewHolder extends BaseAdapter.BaseViewHolder {

        @BindView(R.id.tv_word)
        TextView tvWord;

        @BindView(R.id.tv_word_type)
        TextView tvWordType;

        public WordHeaderViewHolder(View itemView, AdapterWordDetail adapterWordDetail) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            mAdapter = adapterWordDetail;
        }
    }

    class WordDetailViewHolder extends BaseAdapter.BaseViewHolder {

        @BindView(R.id.tv_definition)
        TextView tvDefinition;

        public WordDetailViewHolder(View itemView, AdapterWordDetail adapterWordDetail) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mAdapter = adapterWordDetail;
        }
    }

    class RelatedWordViewHolder extends BaseAdapter.BaseViewHolder {

        @BindView(R.id.rv_word_list)
        RecyclerView rvWordList;

        public RelatedWordViewHolder(View itemView, AdapterWordDetail adapterWordDetail) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mAdapter = adapterWordDetail;
        }
    }
}
