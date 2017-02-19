package com.gjg.learn.customattrs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Junguang_Gao on 2016/12/13.
 */
public class CustomAttrsView extends View {

    private String name;
    private int age;
    private Bitmap background;
    private Paint paint;

    public CustomAttrsView(Context context) {
        this(context,null);
    }

    public CustomAttrsView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomAttrsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //通過命名空间获取属性
        String m_name=attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","name");
        String m_age=attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","age");
        String m_bg=attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","mbackground");

        //通过集合遍历获取属性
        for (int i=0;i<attrs.getAttributeCount();i++){
            System.out.println(attrs.getAttributeName(i)+"---------->"+attrs.getAttributeValue(i));
        }

        //使用系统工具获取属性
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CustomAttrsView);
        for (int i=0;i<typedArray.getIndexCount();i++){
            int index=typedArray.getIndex(i);
            switch (index){
                case R.styleable.CustomAttrsView_name:
                    name=typedArray.getString(index);
                    break;
                case R.styleable.CustomAttrsView_age:
                    age=typedArray.getInt(index,0);
                    break;
                case R.styleable.CustomAttrsView_mbackground:
                    Drawable drawable=typedArray.getDrawable(index);
                    BitmapDrawable bitmapDrawable= (BitmapDrawable) drawable;
                    background=bitmapDrawable.getBitmap();
                    break;
            }
        }
        typedArray.recycle();
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#ff0000"));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(name,5,10,paint);
        canvas.drawText(age+"",60,10,paint);
        canvas.drawBitmap(background,5,30,paint);
    }
}
