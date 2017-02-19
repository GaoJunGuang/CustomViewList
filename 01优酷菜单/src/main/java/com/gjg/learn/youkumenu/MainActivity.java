package com.gjg.learn.youkumenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Junguang_Gao on 2016/12/12.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private ImageView iv_home;
    private ImageView iv_menu;
    private ImageView iv_search;
    private ImageView iv_myyouku;

    private RelativeLayout rl_level2;
    private RelativeLayout rl_level3;
    private RelativeLayout rl_level1;
    /**
     * level2、3的状态
     */
    private boolean isShow_level2=true;
    private boolean isShow_level3=true;
    private boolean isShow_level1=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        iv_home= (ImageView) this.findViewById(R.id.iv_home);
        iv_home.setOnClickListener(this);
        rl_level2= (RelativeLayout) this.findViewById(R.id.rl_level2);
        rl_level3= (RelativeLayout) this.findViewById(R.id.rl_level3);
        rl_level1= (RelativeLayout) this.findViewById(R.id.rl_level1);
        iv_menu= (ImageView) this.findViewById(R.id.iv_menu);
        iv_menu.setOnClickListener(this);
        iv_search= (ImageView) this.findViewById(R.id.iv_search);
        iv_search.setOnClickListener(this);
        iv_myyouku= (ImageView) this.findViewById(R.id.iv_myyouku);
        iv_myyouku.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_home:
                //如果level2和level3都显示
                if(isShow_level2&&isShow_level3){
                    //先后隐藏level3和level2
                    Tool.hideView(rl_level2);
                    Tool.hideView(rl_level3,200);

                    //并将level2和level3标志成隐藏状态
                    isShow_level3=false;
                    isShow_level2=false;
                }else if(isShow_level2){//level3隐藏、level2显示
                    //隐藏level2
                    Tool.hideView(rl_level2);
                    isShow_level2=false;

                }else if((!isShow_level2)){//level3隐藏、level2隐藏
                    //显示level2
                    Tool.showView(rl_level2);
                    isShow_level2=true;
                }

                break;
            case R.id.iv_menu:
                //如果level3显示
                if(isShow_level3){
                    Tool.hideView(rl_level3);
                    isShow_level3=false;
                }else {
                    Tool.showView(rl_level3);
                    isShow_level3=true;
                }
                break;

            case R.id.iv_search:
                Toast.makeText(this,"search button click",Toast.LENGTH_LONG).show();
                break;
            case R.id.iv_myyouku:
                Toast.makeText(this,"myyouku button click",Toast.LENGTH_LONG).show();
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //按下菜单键
        if(keyCode==KeyEvent.KEYCODE_MENU){
            /*if(isShow_level1){

                isShow_level1=false;
                Tool.hideView(rl_level1);
                if(isShow_level2){
                    isShow_level2=false;
                    Tool.hideView(rl_level2,200);

                    if(isShow_level3){
                        //三级菜单都隐藏
                        isShow_level3=false;
                        Tool.hideView(rl_level3,400);

                    }
                }
            }else {

                    //显示二级菜单
                    isShow_level1=true;
                    Tool.showView(rl_level1);
                    isShow_level2=true;
                    Tool.showView(rl_level2,200);

            }*/
            if(isShow_level3&&isShow_level2&&isShow_level1){//三级菜单都显示
                //三级菜单都隐藏
                Tool.hideView(rl_level1);
                isShow_level1=false;
                Tool.hideView(rl_level2,200);
                isShow_level2=false;
                Tool.hideView(rl_level3,400);
                isShow_level3=false;

            }else if((!isShow_level2)&&(!isShow_level1)&&(!isShow_level1)){//三级菜单都隐藏
                //显示二级菜单
                Tool.showView(rl_level1);
                isShow_level1=true;
                Tool.showView(rl_level2,200);
                isShow_level2=true;


            }else if(isShow_level2&&isShow_level1){//level1、2菜单显示
                //隐藏二级菜单
                Tool.hideView(rl_level1);
                isShow_level1=false;
                Tool.hideView(rl_level2,200);
                isShow_level2=false;

            }

            return true;//消费事件
        }

        return super.onKeyDown(keyCode, event);
    }
}
