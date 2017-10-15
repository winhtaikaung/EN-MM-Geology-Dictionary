package com.rangon.en_mmgeologydictionary.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.model.SettingItem;
import com.rangon.en_mmgeologydictionary.presentation.ui.base.BaseAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by winhtaikaung on 15/10/17.
 */

public class AdapterSettingList extends BaseAdapter<BaseAdapter.BaseViewHolder> implements SwitchCompat.OnCheckedChangeListener {

    Context mContext;
    List<SettingItem> mSettingList;
    int FONT_SWITCHER_VIEW = 999;
    int DEFAULT_SETTING_ITEM_VIEW = 111;
    private boolean onBind;

    public AdapterSettingList() {
        mSettingList = new ArrayList<>();
    }

    public void setSettingList(List<SettingItem> settingList) {
        if (!onBind) {
            this.mSettingList = settingList;
            notifyDataSetChanged();
        }
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();


        if (viewType == DEFAULT_SETTING_ITEM_VIEW) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.item_setting_text, parent, false);
            return new AdapterSettingList.SettingsListItemViewHolder(view, this);
        } else if (viewType == FONT_SWITCHER_VIEW) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_font_switcher, parent, false);
            return new AdapterSettingList.SettingsListItemFontSwitchViewHolder(view, this);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_setting_text, parent, false);
            return new AdapterSettingList.SettingsListItemViewHolder(view, this);
        }

    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == DEFAULT_SETTING_ITEM_VIEW) {
            SettingsListItemViewHolder vh = (SettingsListItemViewHolder) holder;
            SettingItem viewItem = (SettingItem) mSettingList.get(position);
            vh.tvTitle.setText(viewItem.getSettingTitle());
            Picasso.with(mContext).load(viewItem.getIconResId()).into(vh.ivItem);
        } else if (viewType == FONT_SWITCHER_VIEW) {
            SettingsListItemFontSwitchViewHolder vh = (SettingsListItemFontSwitchViewHolder) holder;
            SettingItem viewItem = (SettingItem) mSettingList.get(position);
            vh.swChangeFont.setOnCheckedChangeListener(this);
            onBind = true;
//            vh.swChangeFont.setChecked(MySharedPreference.getInstance(mContext).getBooleanPreference(IS_MERCHANT, false));
            onBind = false;

        }
    }

    @Override
    public int getItemCount() {
        return mSettingList != null ? mSettingList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mSettingList.size()-1) {
            return FONT_SWITCHER_VIEW;
        } else {
            return DEFAULT_SETTING_ITEM_VIEW;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    class SettingsListItemViewHolder extends BaseViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.ivItem)
        ImageView ivItem;

        public SettingsListItemViewHolder(View itemView, AdapterSettingList adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            mAdapter = adapter;
        }


    }

    class SettingsListItemFontSwitchViewHolder extends BaseViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.swChangeFont)
        SwitchCompat swChangeFont;

        public SettingsListItemFontSwitchViewHolder(View itemView, AdapterSettingList adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            mAdapter = adapter;
        }


    }


}
