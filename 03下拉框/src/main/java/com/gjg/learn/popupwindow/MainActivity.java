package com.gjg.learn.popupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_input;
    private ImageView iv_arrow;
    private PopupWindow mPopupWindow;
    private ListView mListView;
    private List<String> stringList;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        adapter=new MyAdapter();
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et_input.setText(stringList.get(position));
                if(mPopupWindow != null && mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                    mPopupWindow = null;
                }
            }
        });
    }

    private void initView() {
        et_input= (EditText) this.findViewById(R.id.et_input);
        iv_arrow= (ImageView) this.findViewById(R.id.iv_arrow);
        iv_arrow.setOnClickListener(this);
    }

    private void initData() {
        mListView=new ListView(this);
        mListView.setBackgroundResource(R.drawable.listview_background);
        stringList=new ArrayList<String>();
        for (int i=0;i<150;i++){
            String str=(i<10)?("1"+i+"0"):((i<100)?(i+"4"):i+"");
            stringList.add("44"+str+"2719881111034");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_arrow:
                if(mPopupWindow==null){
                    mPopupWindow=new PopupWindow(this);
                    mPopupWindow.setWidth(et_input.getWidth());
                    int height=DensityUtil.dip2px(this,250);
                    mPopupWindow.setHeight(height);
                    mPopupWindow.setFocusable(true);
                    mPopupWindow.setContentView(mListView);
                }
                mPopupWindow.showAsDropDown(et_input,0,0);
                break;
        }
    }

    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return stringList.size();
        }

        @Override
        public Object getItem(int position) {
            return stringList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView=View.inflate(MainActivity.this,R.layout.item_listview,null);
                holder=new ViewHolder();
                holder.tv_id= (TextView) convertView.findViewById(R.id.tv_id);
                holder.iv_delete= (ImageView) convertView.findViewById(R.id.iv_delete);
                convertView.setTag(holder);

            }else{
                holder= (ViewHolder) convertView.getTag();
            }

            holder.tv_id.setText(stringList.get(position));
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stringList.remove(position);
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }

        class ViewHolder{
            private TextView tv_id;
            private ImageView iv_delete;

        }
    }
}
