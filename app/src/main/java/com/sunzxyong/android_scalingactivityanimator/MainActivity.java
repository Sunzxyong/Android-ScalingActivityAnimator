package com.sunzxyong.android_scalingactivityanimator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    public void start(View view) {
        final ScalingActivityAnimator mScalingActivityAnimator = new ScalingActivityAnimator(this, this, R.id.root_view, R.layout.pop_view);
        switch (view.getId()) {
            case R.id.btn_one:
//                mScalingActivityAnimator.start();
                View popView = mScalingActivityAnimator.start();
                Button mButtonBack = (Button) popView.findViewById(R.id.btn_cancel);
                mButtonBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mScalingActivityAnimator.resume();
                    }
                });
                //...
                break;
            case R.id.btn_two:
                mScalingActivityAnimator.setPopViewHeightIsOneThirdOfScreen();
                mScalingActivityAnimator.start();
                break;
            case R.id.btn_three:
                mScalingActivityAnimator.setPopViewHeightIsTwoThirdOfScreen();
                mScalingActivityAnimator.start();
                break;
        }

    }
}
