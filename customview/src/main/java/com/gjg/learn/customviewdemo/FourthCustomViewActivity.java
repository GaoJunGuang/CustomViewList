package com.gjg.learn.customviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Junguang_Gao on 2016/7/5.
 */
public class FourthCustomViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourthcustomview);

        MyFlowLayout myFlowLayout= (MyFlowLayout) this.findViewById(R.id.myFlowLayout);
        LinearLayout.LayoutParams marginLayoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        marginLayoutParams.setMargins(10,0,10,0);

        TextView textView=new TextView(this);
        textView.setBackgroundResource(R.drawable.tag_bg);
        textView.setText("美丽");
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        textView.setPadding(20,10,20,10);
        textView.setTextSize(25);
        myFlowLayout.addView(textView, marginLayoutParams);

        TextView textView1=new TextView(this);
        textView1.setText("温柔贤淑");
        textView1.setTextSize(25);
        textView1.setPadding(20,10,20,10);
        textView1.setBackgroundResource(R.drawable.tag_bg);
        myFlowLayout.addView(textView1, marginLayoutParams);

        TextView textView2=new TextView(this);
        textView2.setText("可爱");
        textView2.setPadding(20,10,20,10);
        textView2.setTextSize(25);
        textView2.setBackgroundResource(R.drawable.tag_bg);
        myFlowLayout.addView(textView2, marginLayoutParams);

        TextView textView3=new TextView(this);
        textView3.setText("女神级别");
        textView3.setTextSize(25);
        textView3.setPadding(20,10,20,10);
        textView3.setBackgroundResource(R.drawable.tag_bg);
        myFlowLayout.addView(textView3, marginLayoutParams);

        TextView textView4=new TextView(this);
        textView4.setText("吃货");
        textView4.setTextSize(25);
        textView4.setPadding(20,10,20,10);
        textView4.setBackgroundResource(R.drawable.tag_bg);
        myFlowLayout.addView(textView4, marginLayoutParams);


    }
}
