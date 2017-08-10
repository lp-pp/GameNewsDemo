package com.lp.gameclient.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lp.gamenewsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LP on 2017/8/10/17:20.
 */

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.tv_count)
    TextView tv_count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_count)
    public void onClick(View v){
        finishActivity();
    }

    private void finishActivity() {
        this.finish();
        startActivity(new Intent(GuideActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {

    }
}
