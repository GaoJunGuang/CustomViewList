package com.gjg.learn.qyzview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Junguang_Gao on 2016/12/15.
 */
public class CircleProgressView extends View{
    //圆中心显示的名字
    private String cp_name;
    //百分比
    private float cp_value;
    //显示颜色
    private int cp_color;
    //字体大小
    private int cp_textsize;

    private int width;
    private int height;

    private Paint circlePaint;
    private Paint textPaint;
    private Paint arcPaint;
    private RectF arcRectF;//弧形
    private float sweepAngle;//弧形角度
    private float mCircleXY;//圆形位置
    private float mRadius;//圆半径



    public CircleProgressView(Context context) {
        this(context,null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CircleProgressView);
        int count=typedArray.getIndexCount();
        for(int i=0;i<count;i++){
            int index=typedArray.getIndex(i);
            switch (index){
                case R.styleable.CircleProgressView_cp_name:
                    cp_name=typedArray.getString(index);
                    break;
                case R.styleable.CircleProgressView_cp_value:
                    cp_value=typedArray.getFloat(index,30);
                    break;
                case R.styleable.CircleProgressView_cp_color:
                    cp_color=typedArray.getColor(index,0x880000ff);
                    break;
                case R.styleable.CircleProgressView_cp_textsize:
                    cp_textsize=typedArray.getDimensionPixelSize(index,20);
                    break;
            }
        }
        typedArray.recycle();


    }


    private void initView() {
        //处理 padding
        int paddingLeft=getPaddingLeft();
        int paddingRight=getPaddingRight();
        int paddingTop=getPaddingTop();
        int paddingBottom=getPaddingBottom();
        int newWidth=width-paddingLeft-paddingRight;
        int newHeight=height-paddingTop-paddingBottom;
        //使得图形是正方形，边长为length
        float length = 0;
        if (newHeight >= newWidth) {
            length = newWidth;
        } else {
            length = newHeight;
        }

        mCircleXY = length / 2;
        mRadius = (float) (length * 0.5 / 2);

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(cp_color);

        sweepAngle = (cp_value / 100f) * 360f;
        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setColor(cp_color);

        arcPaint.setStyle(Paint.Style.STROKE);
        arcRectF = new RectF(
                (float) (length * 0.1),
                (float) (length * 0.1),
                (float) (length * 0.9),
                (float) (length * 0.9));
        arcPaint.setStrokeWidth((float) (length * 0.15 ));

        textPaint = new Paint();
        textPaint.setTextSize(cp_textsize);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }else if(widthMode==MeasureSpec.AT_MOST){
            int imageWidth=150;//默认值
            Log.e("circle","widthSize--->"+widthSize+"imageWidth--->"+imageWidth);
            width=Math.min(imageWidth,widthSize);
        }

        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else if(heightMode==MeasureSpec.AT_MOST){
            int imageHeight=150;//默认值
            height=Math.min(imageHeight,heightSize);
        }
        setMeasuredDimension(width,height);

        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制圆
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, circlePaint);
        // 绘制弧线
        canvas.drawArc(arcRectF, 270, sweepAngle, false, arcPaint);
        // 绘制文字
        canvas.drawText(cp_name, 0, cp_name.length(),
                mCircleXY, mCircleXY , textPaint);
        String valuePercent="("+cp_value+"%)";
        canvas.drawText(valuePercent,0,valuePercent.length(),
                mCircleXY,mCircleXY+cp_textsize,textPaint);
    }

    /**
     * 设置百分比
     * @param value
     */
    public void setSweepValue(float value) {
        if (value != 0) {
            cp_value = value;
        } else {
            cp_value = 30;
        }
        sweepAngle = (cp_value / 100f) * 360f;
        this.invalidate();
    }

    /**
     * 设置圆中心的内容
     * @param cp_name
     */
    public void setCircleProgressName(String cp_name) {
        this.cp_name = cp_name;
        this.invalidate();
    }

    /**
     * 设置图形的颜色
     * @param cp_color
     */
    public void setImageColor(int cp_color) {
        this.cp_color = cp_color;
        this.invalidate();
    }

    /**
     * 设置显示字体大小
     * @param cp_textsize
     */
    public void setTextSize(int cp_textsize) {
        this.cp_textsize = cp_textsize;
        this.invalidate();
    }
}
