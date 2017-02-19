package com.gjg.learn.myviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * Created by Junguang_Gao on 2016/12/16.
 */
public class MyViewPager extends ViewGroup {
    /**
     * 当前页面的下标位置
     */
    private int currentIndex;
    private float startX;
    private float startY;

    private float mStartX;
    private Scroller scroller;
    private GestureDetector gestureDetector;
    public MyViewPager(Context context) {
        this(context,null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        scroller=new Scroller(context);
        gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                Toast.makeText(context,"长按",Toast.LENGTH_SHORT).show();
            }

            /**
             *
             * @param e1
             * @param e2
             * @param distanceX 在X轴滑动了的距离
             * @param distanceY 在Y轴滑动了的距离
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                /**
                 *x:要在X轴移动的距离
                 *y:要在Y轴移动的距离
                 */
                scrollBy((int)distanceX,0);
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(context,"双击",Toast.LENGTH_SHORT).show();
                return super.onDoubleTap(e);
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        for(int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            child.measure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount =getChildCount();
        for(int i=0;i<childCount;i++){
            View childView=getChildAt(i);
            childView.layout(i*getWidth(),0,(i+1)*getWidth(),getHeight());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        //将触摸事件交给手势识别器处理 作用：使之能够实时滑动，看到滑动的效果
        gestureDetector.onTouchEvent(event);

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //记录按下的x坐标
                mStartX=event.getX();

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                //记录松开手指时的位置
                float endX=event.getX();

                //下标位置
                int tempIndex = currentIndex;
                if((mStartX-endX)>getWidth()/3){//从右向左滑动 显示下一个页面
                    tempIndex++;
                } //从左向右滑动 显示上一个页面
                else if((endX-mStartX)>getWidth()/3){
                    tempIndex--;
                }

                //根据下标位置移动到指定的页面
                scrollToPager(tempIndex);

                break;

        }
        return true;
    }

    public void scrollToPager(int tempIndex) {
        //防止越界
        if(tempIndex < 0){
            tempIndex = 0;
        }
        if(tempIndex > getChildCount()-1){
            tempIndex = getChildCount()-1;
        }
        //当前页面的下标位置
        currentIndex = tempIndex;
        if(mOnPagerChangeListener != null){
            // mOnPagerChangeListener ==MyOnPagerChangeListener;
            //MyOnPagerChangeListener 里面有一个方法，onScrollToPager(int)
            mOnPagerChangeListener.onScrollToPager(currentIndex);
        }
        //总距离计算出来
        int distanceX = currentIndex*getWidth() - getScrollX();
        scroller.startScroll(getScrollX(),getScrollY(),distanceX,0,Math.abs(distanceX));
        invalidate();//强制绘制;//onDraw();computeScroll();
    }

    /**
     *如果没有这个方法，滑动时就会滑到哪便在哪停止
     */
    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            float currX=scroller.getCurrX();
            scrollTo((int) currX,0);
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //防止滑动时产生距离突变
        gestureDetector.onTouchEvent(ev);
        boolean result=false;
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //记录按下的x,y坐标
                startX=ev.getX();
                startY=ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //记录移动手指时的位置
                float endX=ev.getX();
                float endY=ev.getY();

                //计算滑动距离
                float distanceX=Math.abs(endX-startX);
                float distanceY=Math.abs(endY-startY);

                if(distanceX > distanceY&&  distanceX >5){
                    result = true;
                }else{
                    scrollToPager(currentIndex);
                }

                break;
            case MotionEvent.ACTION_UP:

                break;

        }
        return result;//默认不拦截
    }

    /**
     * 监听页面的改变
     */
    public interface OnPagerChangeListener {

        /**
         * 当页面改变的时候回调这个方法
         * @param position 当前页面的下标
         */
        void onScrollToPager(int position);
    }

    private OnPagerChangeListener mOnPagerChangeListener;

    /**
     * 设置页面改变的监听
     * @param l
     */
    public void setOnPagerChangeListener( OnPagerChangeListener l) {
        //当前的 mOnPagerChangListener==MyOnPagerChangListener；
        mOnPagerChangeListener = l;
    }
}
