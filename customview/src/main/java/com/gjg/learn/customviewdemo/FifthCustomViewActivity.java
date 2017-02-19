package com.gjg.learn.customviewdemo;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Junguang_Gao on 2016/8/9.
 */
public class FifthCustomViewActivity extends Activity {
    private FifthCustomView fifthCustomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fifthcustomview);
        fifthCustomView= (FifthCustomView) this.findViewById(R.id.cv);
        /*
         * 开线程
         */
        new Thread(fifthCustomView).start();
    }
}
