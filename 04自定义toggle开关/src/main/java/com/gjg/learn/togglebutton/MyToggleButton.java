package com.gjg.learn.togglebutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Junguang_Gao on 2016/12/13.
 *  一个视图从创建到显示过程中的主要方法
 * //1.构造方法实例化类
 * //2.测量-measure(int,int)-->onMeasure();
 * 如果当前View是一个ViewGroup,还有义务测量孩子
 * 孩子有建议权
 * //3.指定位置-layout()-->onLayout();
 * 指定控件的位置，一般View不用写这个方法，ViewGroup的时候才需要，一般View不需要重写该方法
 * //4.绘制视图--draw()-->onDraw(canvas)
 * 根据上面两个方法参数，进入绘制
 */
public class MyToggleButton extends View implements View.OnClickListener {
    private Paint mPaint;
    private Bitmap mBackgroundBitmap;
    private Bitmap mToggleButton;
    private int slidLeftMax;//滑动的最大距离
    private int slidLeft;//当前滑动距离
    private boolean isOpen = false;//是否打开状态
    private float lastX;
    private float startX;


    public MyToggleButton(Context context) {
        this(context,null);
    }

    public MyToggleButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //初始化画笔以及设置画笔相关属性
        mPaint=new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        //获得背景图片
        mBackgroundBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.switch_background);
        //获取togglebutton
        mToggleButton=BitmapFactory.decodeResource(getResources(),R.drawable.slide_button);
        //滑动的最大距离
        slidLeftMax=mBackgroundBitmap.getWidth()-mToggleButton.getWidth();
        //设置监听事件
        setOnClickListener(this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量背景图片的宽、高并设置
        setMeasuredDimension(mBackgroundBitmap.getWidth(),mBackgroundBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBackgroundBitmap,0,0,mPaint);//画背景
        canvas.drawBitmap(mToggleButton,slidLeft,0,mPaint);//画toggleButton

    }

    /**
     * true:点击事件生效，滑动事件不生效
     * false:点击事件不生效，滑动事件生效
     */
    private boolean isEnableClick = true;
    @Override
    public void onClick(View v) {
       if(isEnableClick){
           //刷新view
           flushView();
       }

    }

    private void flushView() {
        if(isOpen){//当前为打开状态
            isOpen=false;
            slidLeft=slidLeftMax;
        }else{
            isOpen=true;
            slidLeft=0;
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);//必须执行父类的方法，若不执行父类方法，点击事件不生效
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //记录按下的位置
                startX=event.getX();//相对位置
                lastX=startX;
                isEnableClick=true;
                break;
            case MotionEvent.ACTION_MOVE:
                //记录滑动结束的位置
                float endX=event.getX();
                //计算滑动距离
                float distanceX=endX-startX;
                //当前滑动距离
                slidLeft+=distanceX;
                //防止边界越界
                if(slidLeft<0){
                    slidLeft=0;
                }else if(slidLeft>slidLeftMax){
                    slidLeft=slidLeftMax;
                }
                invalidate();
                //重新设置按下的位置
                startX=event.getX();
                //判断是滑动还是点击
                if(Math.abs(endX-lastX)>5){
                    isEnableClick=false;
                }

                break;
            case MotionEvent.ACTION_UP:

                if(!isEnableClick){//滑动

                    //回弹效果
                    if(slidLeft>slidLeftMax/2){
                        isOpen=true;
                    }else{
                        isOpen=false;
                    }
                    flushView();
                }
                break;
        }
        return true;

    }
}
