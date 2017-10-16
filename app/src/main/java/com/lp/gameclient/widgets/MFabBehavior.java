package com.lp.gameclient.widgets;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * Created by LP on 2017/10/16/10:50.
 */

public class MFabBehavior extends CoordinatorLayout.Behavior<View>{
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private float viewY = 0; //距离控件coordinatorLayout底部距离
    private boolean isAnimate; //动画是否在进行

    public MFabBehavior(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        if (child.getVisibility() == View.VISIBLE && viewY == 0) {
            //获取控件距离父布局（coordinatorLayout）底部距离
            viewY = coordinatorLayout.getHeight() - child.getY();
        }
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0; //判断是否竖直滚动
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {
        //大于0是向上滚动 小于0是向下滚动
        if (dy > 64 && !isAnimate && child.getVisibility() == View.VISIBLE){
            hide(child);
        } else if (dy < 0 && isAnimate && child.getVisibility() == View.GONE){
            show(child);
        }
    }

    //隐藏时的动画
    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(viewY).setInterpolator(INTERPOLATOR).setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    //显示时的动画
    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
