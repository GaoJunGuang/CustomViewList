package com.gjg.learn.frameapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout layout;
    private Button bt_scrollto;
    private Button bt_scrollby;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        /*layout= (LinearLayout) this.findViewById(R.id.layout_ll);
        bt_scrollto= (Button) this.findViewById(R.id.bt_scrollto);
        bt_scrollby= (Button) this.findViewById(R.id.bt_scrollby);
        bt_scrollto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollTo(-60, -100);
            }
        });
        bt_scrollby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollBy(-60, -100);
            }
        });*/
    }
}
