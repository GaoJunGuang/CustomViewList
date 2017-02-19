package com.gjg.learn.qyzview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Junguang_Gao on 2016/12/19.
 */
public class MyScrollView extends ViewGroup {
    private Scroller mScroller;
    private GestureDetector detector;
    //当前的页面
    private int currentPager;

    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mScroller=new Scroller(context);
        detector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                scrollBy(0, (int) distanceY);
                return true;
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount=getChildCount();
        for(int i=0;i<childCount;i++){
            View childView=getChildAt(i);
            childView.layout(0,i*getHeight(),getWidth(),(i+1)*getHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount=getChildCount();
        for (int i=0;i<childCount;i++){
            View childView=getChildAt(i);
            childView.measure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    private float startY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        detector.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                float endY=event.getY();
                int tempIndex=currentPager;
                //从上往下滑动
                if((endY-startY)>getHeight()/3){
                    //显示上一页
                    tempIndex--;

                //从下往上滑动
                }else if((startY-endY)>getHeight()/3){
                    //显示下一页
                    tempIndex++;
                }
                scrollToPager(tempIndex);
                break;
        }
        return true;
    }

    private void scrollToPager(int tempIndex) {
        //防止越界
        if(tempIndex<0){
            tempIndex=0;
        }
        if(tempIndex>getChildCount()-1){
            tempIndex=getChildCount()-1;
        }
        currentPager=tempIndex;
        // int distanceX = 目标 - getScrollX();
        int distanceY=currentPager*getHeight()-getScrollY();
        mScroller.startScroll(getScrollX(),getScrollY(),0,distanceY);

        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            float currentY=mScroller.getCurrY();
            scrollTo(0, (int) currentY);
            //Log.d("gjg","getScrollX()--->"+getScrollX()+"======getScrollY()"+getScrollY());
            invalidate();
        }
    }
}
