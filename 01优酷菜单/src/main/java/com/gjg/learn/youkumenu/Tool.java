package com.gjg.learn.youkumenu;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Junguang_Gao on 2016/12/12.
 */
public class Tool {

    public static void showView(View view){
        showView(view,0);
    }

    public static void hideView(View view){
        hideView(view,0);
    }

    public static void showView(View view,int delay){
        //设置旋转中心
        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(view,"rotation",180,360);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        objectAnimator.setStartDelay(delay);

        /*RotateAnimation rotateAnimation=new RotateAnimation(180,360,view.getWidth()/2,view.getHeight());
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setStartOffset(delay);
        view.startAnimation(rotateAnimation);*/


    }

    public static void hideView(View view,int delay){
        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(view,"rotation",0,180);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        objectAnimator.setStartDelay(delay);


       /* RotateAnimation rotateAnimation=new RotateAnimation(0,180,view.getWidth()/2,view.getHeight());
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setStartOffset(delay);
        view.startAnimation(rotateAnimation);*/

    }
}
