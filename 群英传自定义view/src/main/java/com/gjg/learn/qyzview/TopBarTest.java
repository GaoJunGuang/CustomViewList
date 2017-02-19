package com.gjg.learn.qyzview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Junguang_Gao on 2016/12/15.
 */
public class TopBarTest extends Activity{
    private TopBarLayout mTopbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topbar_test);
        // 获得我们创建的topbar
        mTopbar = (TopBarLayout) findViewById(R.id.topBar);
        // 为topbar注册监听事件，传入定义的接口
        // 并以匿名类的方式实现接口内的方法
        mTopbar.setOnTopBarClickListener(
                new TopBarLayout.topBarClickListener() {

                    @Override
                    public void rightClick() {
                        Toast.makeText(TopBarTest.this,
                                "right", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void leftClick() {
                        Toast.makeText(TopBarTest.this,
                                "left", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        // 控制topbar上组件的状态
        mTopbar.setButtonVisable(0, true);
        mTopbar.setButtonVisable(1, true);
    }
}
