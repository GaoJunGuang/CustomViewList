package com.gjg.learn.qyzview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Junguang_Gao on 2016/12/14.
 */
public class MyTextView extends TextView {
    private Paint mPaint1,mPaint2;
    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint1=new Paint();
        mPaint1.setColor(Color.parseColor("#ff47ff"));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2=new Paint();
        mPaint2.setColor(Color.parseColor("#ffff00"));
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint1);
        canvas.drawRect(10,10,getWidth()-10,getHeight()-10,mPaint2);
        Log.d("gjg","getWidth()--->"+getWidth()+"getHeight()"+getHeight());
        Log.d("gjg","getMeasuredWidth()--->"+getMeasuredWidth()+"getMeasuredHeight()---->"+getMeasuredHeight());
//        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint1);
//        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,mPaint2);

        canvas.save();//保存画布状态
//        canvas.rotate(90,getMeasuredWidth()/2,getMeasuredHeight()/2);//正值代表顺时针旋转
        // 绘制文字前平移10像素

        super.onDraw(canvas);//调用父类方法绘制text
        canvas.restore();//取出上一次保存的画布的状态
    }
}
