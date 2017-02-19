package com.gjg.learn.customviewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Junguang_Gao on 2016/7/5.
 */
public class ThirdCustomViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.thirdcustomview);

        LinearLayout linearLayout= (LinearLayout) this.findViewById(R.id.ll);
       // LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        CollapseView view1=new CollapseView(this);
        view1.setNumber("1");
        view1.setTitle("女友");
        view1.setContentImage(R.drawable.meinv2);
        linearLayout.addView(view1);

        CollapseView view2=new CollapseView(this,null);
        view2.setNumber("2");
        view2.setTitle("女神");
        view2.setContent(R.layout.meinv1);
        linearLayout.addView(view2);

        CollapseView view3=new CollapseView(this,null);
        view3.setNumber("3");
        view3.setTitle("女屌丝");
        linearLayout.addView(view3);

    }



}
