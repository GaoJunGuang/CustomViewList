package com.gjg.learn.qyzview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Junguang_Gao on 2016/12/15.
 * 动态音频图形
 */
public class VoiceFrequencyView extends View {
    //音频图的宽度
    private int mWidth;
    //每一条音频的宽度
    private int mRectWidth;
    //每一条音频的高度
    private int mRectHeight;
    private Paint mPaint;
    //音频的条数
    private int mRectCount;
    //每一条音频之间的距离
    private int offset = 5;
    private double mRandom;
    //线性渲染器
    private LinearGradient mLinearGradient;

    public VoiceFrequencyView(Context context) {
        this(context,null);
    }

    public VoiceFrequencyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VoiceFrequencyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#770000ff"));
        mRectCount=15;
    }

    /**
     * onSizeChange() 方法在view第一次被指定了大小值、或者view的大小发生改变时会被调用。
     * 所以一般用来计算一些位置和与view的size有关的值。
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setPaintShader();
    }

    private void setPaintShader() {
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int) (mWidth * 0.8 / mRectCount);
        mLinearGradient = new LinearGradient(
                0,
                0,
                mRectWidth,
                mRectHeight,
                Color.YELLOW,
                Color.BLUE,
                Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++) {
            mRandom = Math.random();
            //当前音频条的高度
            float currentHeight = (float) (mRectHeight * mRandom);
            canvas.drawRect(
                    (float) (mWidth * 0.2 / 2 + mRectWidth * i + offset),
                    currentHeight,
                    (float) (mWidth * 0.2 / 2 + mRectWidth * (i + 1)),
                    mRectHeight,//固定值，使得每一条音频条都是从同一起点画起
                    mPaint);
        }
        postInvalidateDelayed(500);
    }
}
