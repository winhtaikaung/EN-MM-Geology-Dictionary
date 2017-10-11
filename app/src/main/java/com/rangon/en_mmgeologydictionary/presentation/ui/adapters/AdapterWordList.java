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

    public AdapterWordList()
        mWordList = new ArrayList<>();
    }

    public void setWordList(List<Word> wordList) {
        this.mWordList = wordList;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_word_list, parent, false);
        return new WordListViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) != TYPE_PENDING) {
            WordListViewHolder vh = (WordListViewHolder) holder;
            if (vh != null) {
                if (mWordList.get(position) != null) {
                    Word wordItem = mWordList.get(position);

                    vh.tvWord.setText(wordItem.getWord());
                    vh.tvDefinition.setText(wordItem.getMeaningUni());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mWordList != null ? mWordList.size() : 0;
    }

    class WordListViewHolder extends BaseViewHolder {

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
}
