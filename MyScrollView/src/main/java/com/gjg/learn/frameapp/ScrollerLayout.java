package com.gjg.learn.frameapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Junguang_Gao on 2016/5/18.
 */
public class ScrollerLayout extends ViewGroup {
    /**
     * 用于完成滚动操作的实例
     */
    private Scroller mScroller;

    /**
     * 判定为拖动的最小移动像素数
     */
    private int minTouchSlop;

    /**
     * 手按下时的屏幕坐标
     */
    private float mXDown;

    /**
     * 手当时所处的屏幕坐标
     */
    private float mXMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;

    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;

    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        //minTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        //是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件
        minTouchSlop = configuration.getScaledTouchSlop();
        Log.e("--GJG--","minTouchSlop----->"+minTouchSlop);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //可以用下面的方法代替
       // measureChildren(widthMeasureSpec,heightMeasureSpec);
        int count=getChildCount();
        for(int i=0;i<count;i++){
            View childView=getChildAt(i);
            //为每一个childView 测量大小
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int childCount=getChildCount();
            for(int i=0;i<childCount;i++){
                View childView=getChildAt(i);
                childView.layout(i*childView.getMeasuredWidth(),0,(i+1)*getMeasuredWidth(),childView.getMeasuredHeight());
            }
        }
        //初始化左右边界值
        leftBorder=getChildAt(0).getLeft()-50;
        rightBorder=getChildAt(getChildCount()-1).getRight()+50;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("--GJG--","dispatchTouchEvent----->");
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 拦截触摸事件  没用的
     * @param event
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.e("--GJG--","onInterceptTouchEvent----->");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mXDown = event.getRawX();
                mXLastMove = mXDown;
                Log.e("--GJG--","mXDown----->"+mXDown);
                break;
            case MotionEvent.ACTION_MOVE://根本就来不到这一步,出来在刚打开界面时会进入这一步，其他时间都不会进入这一步
                mXMove = event.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                Log.e("--GJG--","diff----->"+diff+"     mXMove------>"+mXMove);
                mXLastMove = mXMove;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > minTouchSlop) {
                    Log.e("--GJG--","onInterceptTouchEvent----->");
                    int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                    int dx = targetIndex * getWidth() - getScrollX();
                    mScroller.startScroll(getScrollX(),0,dx,0);
                    return true;//返回true不在传递到onTouchEvent,返回false继续传递到onTouchEvent
                }
                break;

        }
        return super.onInterceptTouchEvent(event);//默认返回的是false，也就是不拦截了就不会到move那一步的判断了
    }


    /**
     * 触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove - mXMove);//一开始：大-小=正 向左滑动
                //边界保护
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                break;
            case MotionEvent.ACTION_DOWN:
                mXDown = event.getRawX();
                mXLastMove = mXDown;
                break;
        }
        return true;
    }


    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {//当想要知道新的位置时，调用此函数。如果返回true，表示动画还没有结束。位置改变以提供一个新的位置
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
