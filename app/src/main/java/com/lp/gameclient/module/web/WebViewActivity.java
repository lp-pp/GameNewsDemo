package com.lp.gameclient.module.web;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lp.gameclient.base.BaseActivity;
import com.lp.gameclient.utils.ActivityUtil;
import com.lp.gameclient.utils.WebViewConfig;
import com.lp.gamenewsdemo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LP on 2017/10/13/15:00.
 */

public class WebViewActivity extends BaseActivity{

    @BindView(R.id.llayout_container_base)
    LinearLayout mLLayoutContainer;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private WebView mWebView;

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.removeAllViews();
        mWebView.clearHistory();
        mWebView.destroy();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_baseview_toolbar;
    }

    @Override
    protected View getToolBarView() {
        return findViewById(R.id.toolbar);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String url = intent.getExtras().getString(ActivityUtil.ARG_1);
        if (!TextUtils.isEmpty(url)){
            mWebView.loadUrl(url);
            setWebTitle();
        }
    }

    private void setWebTitle() {
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTvTitle.setText(title);
            }
        });
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        mWebView = new WebView(this);
        WebViewConfig.initWebView(this, mWebView);
        mLLayoutContainer.addView(mWebView);
    }

    @OnClick(R.id.btn_back)
    public void onClick(View view){
        confirmQuit();
    }

    @Override
    public void onBackPressed() {
        confirmQuit();
    }

    private void confirmQuit() {
        if (mWebView != null && mWebView.canGoBack()){
            mWebView.goBack();
        } else {
            finish();
        }
    }

}
