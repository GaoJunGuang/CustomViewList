package com.gjg.learn.quicklyindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Junguang_Gao on 2016/12/21.
 */
public class IndexView extends View {
    private Paint mPaint;
    private int mItemWidth;//每个字母条目的宽度
    private int mItemHeight;//每个字母条目的高度
    private int wordIndex=-1;//按下时字母下标

    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    public IndexView(Context context) {
        this(context,null);
    }

    public IndexView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体字
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mItemWidth=getMeasuredWidth();
        mItemHeight=getMeasuredHeight()/words.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       for(int i=0;i<words.length;i++){
           //设置按下时字母的颜色
           if(wordIndex ==i){
               //设置灰色
               mPaint.setColor(Color.GRAY);
           }else{
               //设置白色
               mPaint.setColor(Color.WHITE);
           }

           String word=words[i];
           Rect rect=new Rect();
           //将字母用一个矩形框起来
           mPaint.getTextBounds(word,0,1,rect);
           int mWordWidth=rect.width();
           int mWordHeight=rect.height();
           //每一个字母的位置
           float wordX=mItemWidth/2-mWordWidth/2;
           float wordY=mItemHeight/2+mWordHeight/2+mItemHeight*i;
           canvas.drawText(words[i],wordX,wordY,mPaint);
       }
    }

    /**
     * 手指按下文字变色
     * 1.重写onTouchEvent(),返回true,在down/move的过程中计算
     * int wordIndex = Y / itemHeight; 强制绘制
     * <p/>
     * 2.在onDraw()方法对于的下标设置画笔变色
     * <p/>
     * 3.在up的时候
     * wordIndex  = -1；
     * 强制绘制
     *
     * @param event
     * @return
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float Y = event.getY();
                int index = (int) (Y/mItemHeight);//字母索引
                if(index != wordIndex){

                    wordIndex = index;
                    invalidate();//强制绘制onDraw();
                    if(onIndexChangeListener != null&& wordIndex < words.length){
                        onIndexChangeListener.onIndexChange(words[wordIndex]);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                wordIndex = -1;
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 字母下标索引变化的监听器
     */
    public interface OnIndexChangeListener{

        /**
         * 当字母下标位置发生变化的时候回调
         * @param word 字母（A~Z）
         */
        void onIndexChange(String word);
    }

    private OnIndexChangeListener onIndexChangeListener;

    /**
     * 设置字母下标索引变化的监听
     * @param onIndexChangeListener
     */
    public void setOnIndexChangeListener(OnIndexChangeListener onIndexChangeListener) {
        this.onIndexChangeListener = onIndexChangeListener;
    }
}
