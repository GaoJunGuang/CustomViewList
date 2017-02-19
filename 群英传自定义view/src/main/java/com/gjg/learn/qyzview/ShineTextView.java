package com.gjg.learn.qyzview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Junguang_Gao on 2016/12/14.
 * 动态文字闪烁效果
 */
public class ShineTextView extends TextView {

    private LinearGradient linearGradient;//线性闪烁
    private Matrix matrix;
    private Paint mPaint;
    private int mViewWidth = 0;
    private int mTranslate = 0;

    public ShineTextView(Context context) {
        this(context, null);
    }

    public ShineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();//获得控件的宽度
            if (mViewWidth > 0) {
                mPaint = getPaint();//获取当前绘制textview的paint
                linearGradient = new LinearGradient(
                        0,
                        0,
                        mViewWidth,
                        0,
                        new int[]{
                                Color.BLUE, 0xffffffff, Color.CYAN
                        },
                        null,
                        Shader.TileMode.CLAMP);
                mPaint.setShader(linearGradient);
                matrix = new Matrix();
            }
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (matrix != null) {
            mTranslate += mViewWidth / 5;//闪烁移动距离
            //重新开始闪烁，並且有时间间距
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
//            if(mTranslate>1.5*mViewWidth){
//                mTranslate=0;
//            }
            matrix.setTranslate(mTranslate, 0);
            linearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(100);
        }


    }
}
