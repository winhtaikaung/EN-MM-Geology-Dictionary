package com.rangon.en_mmgeologydictionary.model;

/**
 * Created by winhtaikaung on 17/5/17.
 */

public class SettingItem {

    private String avatarUrl;
    private String userName;
    private String settingTitle;
    private int iconResId;
    private boolean isSwitch = false;
    private boolean isChecked = false;

    public SettingItem SettingAvatarItem(String avatarUrl, String username) {
        this.avatarUrl = avatarUrl;
        this.userName = username;
        return this;
    }

    public SettingItem SettingListItem(String settingTitle, int iconResId) {
        this.settingTitle = settingTitle;
        this.iconResId = iconResId;
        return this;
    }

    public SettingItem SettingListItem(String settingTitle, int iconResId, boolean isSwitch, boolean isChecked) {
        this.settingTitle = settingTitle;
        this.iconResId = iconResId;
        this.setSwitch(isSwitch);
        this.setChecked(isChecked);
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSettingTitle() {
        return settingTitle;
    }

    public void setSettingTitle(String settingTitle) {
        this.settingTitle = settingTitle;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public boolean isSwitch() {
        return isSwitch;
    }

    public void setSwitch(boolean aSwitch) {
        isSwitch = aSwitch;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
