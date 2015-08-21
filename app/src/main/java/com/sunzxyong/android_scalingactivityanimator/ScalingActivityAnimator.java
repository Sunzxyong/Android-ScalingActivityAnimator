package com.sunzxyong.android_scalingactivityanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by 晓勇 on 2015/8/21 0021.
 */
public class ScalingActivityAnimator {
    private Activity mActivity;
    private View mMainView;
    private View mPopupView;
    private PopupWindow mPopupWindow;
    private long duration =400;
    private int screenHeight;
    private int popHeight ;
    /**
     * @param context
     * @param act
     * @param mainViewId main layout id
     * @param displayLayoutId displayLayoutId
     */
    public ScalingActivityAnimator(Context context, Activity act, int mainViewId, int displayLayoutId){
        this.mActivity = act;
        this.mMainView = act.findViewById(mainViewId);
        this.mPopupView = LayoutInflater.from(context).inflate(displayLayoutId, null);
        Point outSize = new Point();
        mActivity.getWindowManager().getDefaultDisplay().getSize(outSize);
        screenHeight = outSize.y;
        popHeight = screenHeight/2;
    }

    /**
     * Open Animation
     * @return pop layout view
     */
    public View start(){
        startAnim();
        return mPopupView;
    }

    /**
     * Close Animation
     */
    public void resume(){
        if(mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
            resumeAnim();
        }
    }
    private void startAnim() {
        int mainViewHeight = mMainView.getRootView().getHeight();//得到根视图View的高度
        ObjectAnimator mainViewScaleXAnim = ObjectAnimator.ofFloat(mMainView, "scaleX", 1.0f, 0.8f);
        ObjectAnimator mainViewScaleYAnim = ObjectAnimator.ofFloat(mMainView, "scaleY", 1.0f, 0.9f);
        ObjectAnimator mainViewAlphaAnim = ObjectAnimator.ofFloat(mMainView, "alpha", 1.0f, 0.5f);
        ObjectAnimator mainViewRotationXAnim = ObjectAnimator.ofFloat(mMainView, "rotationX", 0f, 8f);
        ObjectAnimator mainViewRotationXAnimResume = ObjectAnimator.ofFloat(mMainView, "rotationX", 8f, 0f);
        mainViewRotationXAnimResume.setStartDelay(200);
        ObjectAnimator mainViewTranslationYAnim = ObjectAnimator.ofFloat(mMainView, "translationY", 0, -(mainViewHeight / 20));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(mainViewScaleXAnim, mainViewScaleYAnim, mainViewAlphaAnim, mainViewRotationXAnim, mainViewRotationXAnimResume, mainViewTranslationYAnim);
        animatorSet.setDuration(duration);
        animatorSet.start();
        showPopupWindow();
    }
    private void showPopupWindow() {
        mPopupWindow = new PopupWindow(mPopupView, ViewGroup.LayoutParams.MATCH_PARENT, popHeight, true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.showScalingAnimation);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                resumeAnim();
            }
        });
        mPopupWindow.showAtLocation(mPopupView, Gravity.BOTTOM, 0, 0);
    }
    private void resumeAnim() {
        int mainViewHeight = mMainView.getRootView().getHeight();//得到根视图View的高度
        ObjectAnimator mainViewScaleXAnim = ObjectAnimator.ofFloat(mMainView, "scaleX", 0.8f, 1.0f);
        ObjectAnimator mainViewScaleYAnim = ObjectAnimator.ofFloat(mMainView, "scaleY", 0.9f, 1.0f);
        ObjectAnimator mainViewAlphaAnim = ObjectAnimator.ofFloat(mMainView, "alpha", 0.5f, 1.0f);
        ObjectAnimator mainViewRotationXAnim = ObjectAnimator.ofFloat(mMainView, "rotationX", 0f, 8f);
        ObjectAnimator mainViewRotationXAnimResume = ObjectAnimator.ofFloat(mMainView, "rotationX", 8f, 0f);
        mainViewRotationXAnimResume.setStartDelay(200);
        ObjectAnimator mainViewTranslationYAnim = ObjectAnimator.ofFloat(mMainView, "translationY", -(mainViewHeight / 20), 0);//将缩小后的主View向上平移height/20的高度，即在标题栏下方
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(mainViewScaleXAnim, mainViewScaleYAnim, mainViewAlphaAnim, mainViewRotationXAnim, mainViewRotationXAnimResume, mainViewTranslationYAnim);
        animatorSet.setDuration(duration);
        animatorSet.start();
    }

    /**
     * @return get Animated duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * @param duration animation duration in milliseconds
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * Setting pop-up view is one-third the height of the screen
     */
    public void setPopViewHeightIsOneThirdOfScreen(){
        popHeight = screenHeight/3;
    }

    /**
     * Setting pop-up view is two-thirds the height of the screen
     */
    public void setPopViewHeightIsTwoThirdOfScreen(){
        popHeight = 2*screenHeight/3;
    }
}
