package com.template.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;


public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int TYPE_ADD = 0;//Add功能
    public static final int TYPE_CLEAR = 1;//清除功能
    public static final int TYPE_BLOG = 2;//Blog

    private boolean isShowRight;
    private int rightType;

    @LayoutRes
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            //暂时给MVVM架构使用的
            setContentView(getLayoutId());
        } else {
            setContentView();
        }
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        initViews();
        initEvents();
    }

    public void setContentView() {
    }

    public void initViews() {
    }

    public void initEvents() {
    }

    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp) {
        initToolBar(toolbar, name, showHomeAsUp, false);
    }

    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp, boolean isShowRight) {
        initToolBar(toolbar, name, showHomeAsUp, isShowRight, TYPE_ADD);
    }

    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp, boolean isShowRight, int rightType) {
        this.isShowRight = isShowRight;
        this.rightType = rightType;
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(showHomeAsUp);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    public void openWebView() {

    }

    @Override
    public void onClick(View v) {

    }

    public void add() {
    }

    public void clearCache() {

    }

    /**
     * @param str 弹出的文字
     */
    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
