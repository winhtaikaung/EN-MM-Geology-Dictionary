package com.rangon.en_mmgeologydictionary.presentation.base;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.data.sharedPref.MySharedPref;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by winhtaikaung on 9/10/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolBar;
    @Inject
    protected MySharedPref mySharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/maven-pro-regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(getLayoutResource());
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        toolBar = (Toolbar) this.findViewById(R.id.toolbar);
        if (toolBar != null) {

            toolBar.setTitleTextColor(this.getResources().getColor(R.color.textColor));
            setSupportActionBar(toolBar);
            if (getSupportActionBar() != null) {
                setDisplayHomeEnabled(true);
            }
            final Drawable upArrow = getResources().getDrawable(R.drawable.nav_back_arrow);
            upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }

    }

    protected abstract int getLayoutResource();

    public void setDisplayHomeEnabled(boolean b) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(b);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        toolBar.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        toolBar.setTitle(titleId);
    }

    public Toolbar getToolBar() {
        return toolBar;
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
