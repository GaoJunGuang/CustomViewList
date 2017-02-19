package com.gjg.learn.qyzview;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Junguang_Gao on 2016/12/14.
 */
public class ShowCustomView extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag=getIntent().getIntExtra("flag",-1);
        switch (flag) {
            case 0:
                setContentView(R.layout.simpleview);
                break;
            case 1:
                setContentView(R.layout.my_textview);
                break;
            case 2:
                setContentView(R.layout.shine_textview);
                break;
            case 3:
                setContentView(R.layout.circle_progress);
//                CircleProgressView circle = (CircleProgressView) findViewById(R.id.circle);
//                circle.setSweepValue(52.9f);
//                circle.setCircleProgressName("魅族手机");
//                circle.setImageColor(Color.RED);
//                circle.setTextSize(20);
                break;
            case 4:
                setContentView(R.layout.voice_frequency);
                break;
            case 5:
                setContentView(R.layout.my_scrollview);
                break;
        }
    }
}
