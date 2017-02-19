package com.gjg.learn.qyzview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Junguang_Gao on 2016/12/14.
 */
public class SimpleView extends View {

    public SimpleView(Context context) {
        super(context);
    }

    public SimpleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //調用關鍵的方法
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int width) {
        int resultWidth=0;
        int widthMode=MeasureSpec.getMode(width);
        int widthSize=MeasureSpec.getSize(width);

        Log.d("gjg","widthSize----->"+widthSize);
        if(widthMode==MeasureSpec.EXACTLY){//设定了精确值、match_parent
            resultWidth=widthSize;
        }else{
            resultWidth=300;
            if(widthMode==MeasureSpec.AT_MOST){//设定了wrap_content
                resultWidth=Math.min(resultWidth,widthSize);
            }
        }

        return resultWidth;
    }
    private int measureHeight(int height) {
        int resultHeight=0;
        int heightMode=MeasureSpec.getMode(height);
        int heightSize=MeasureSpec.getSize(height);
        Log.d("gjg","heightSize----->"+heightSize);
        if(heightMode==MeasureSpec.EXACTLY){
            resultHeight=heightSize;
        }else {
            resultHeight=100;
            if(heightMode==MeasureSpec.AT_MOST){
                resultHeight=Math.min(resultHeight,heightSize);
            }
        }
        return resultHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.RED);
        int width=getWidth();
        int height=getHeight();
        Log.d("gjg","width----->"+width+"      height----->"+height);
    }
}
