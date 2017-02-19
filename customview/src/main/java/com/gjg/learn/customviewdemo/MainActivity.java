package com.gjg.learn.customviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button bt_first;
    private Button bt_second;
    private Button bt_third;
    private Button bt_fourth;
    private Button bt_fifth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        bt_first= (Button) this.findViewById(R.id.bt_firstcustomview);
        bt_first.setOnClickListener(this);
        bt_second= (Button) this.findViewById(R.id.bt_secondcustomview);
        bt_second.setOnClickListener(this);
        bt_third= (Button) this.findViewById(R.id.bt_thirdcustomview);
        bt_third.setOnClickListener(this);
        bt_fourth= (Button) this.findViewById(R.id.bt_fourthcustomview);
        bt_fourth.setOnClickListener(this);
        bt_fifth= (Button) this.findViewById(R.id.bt_fifthcustomview);
        bt_fifth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.bt_firstcustomview:
                intent=new Intent(this, FirstCustomViewActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_secondcustomview:
                intent=new Intent(this, SecondCustomViewActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_thirdcustomview:
                intent=new Intent(this, ThirdCustomViewActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_fourthcustomview:
                intent=new Intent(this, FourthCustomViewActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_fifthcustomview:
                intent=new Intent(this, FifthCustomViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
