package com.gjg.learn.viewpage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager vp_image;
    private TextView tv_title;
    private LinearLayout ll_indicator;
    private List<ImageView> imageViews;
    // 图片资源ID
    private final int[] imageIds = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
    };

    // 图片标题集合
    private final String[] imageDescriptions = {
            "尚硅谷拔河争霸赛！",
            "凝聚你我，放飞梦想！",
            "抱歉没座位了！",
            "7月就业名单全部曝光！",
            "平均起薪11345元"
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int item = vp_image.getCurrentItem()+1;
            vp_image.setCurrentItem(item);

            //延迟发消息
            handler.sendEmptyMessageDelayed(0,3000);
        }
    };
    private String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDatas();
        MyPagerAdapter adapter=new MyPagerAdapter(this,imageViews,imageDescriptions);
        vp_image.setAdapter(adapter);
        MyOnPagerChangeListener onPagerChangeListener=new MyOnPagerChangeListener();
        vp_image.addOnPageChangeListener(onPagerChangeListener);

        //设置中间位置
        int item = Integer.MAX_VALUE/2 - Integer.MAX_VALUE/2%imageViews.size();//要保证imageViews的整数倍
        vp_image.setCurrentItem(item);
        tv_title.setText(imageDescriptions[prePosition]);
        //发消息 实现轮播效果
        handler.sendEmptyMessageDelayed(0,3000);
    }

    private void initDatas() {
        imageViews=new ArrayList<ImageView>();
        for (int i=0;i<imageIds.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            imageViews.add(imageView);
            //添加点
            ImageView point=new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);

            //设置点的大小
            int width=DensityUtil.dip2px(this,10);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width,width);

            //刚加载页面时，显示第一个点为选中状态
            if(i==0){
                point.setEnabled(true);//可用：选中状态
            }else {
                point.setEnabled(false);//不可用：非选中状态
                layoutParams.leftMargin=width;//设置间距

            }
            point.setLayoutParams(layoutParams);
            //将点加进去
            ll_indicator.addView(point);


        }
    }

    private void initView() {
        vp_image = (ViewPager) this.findViewById(R.id.vp_image);
        tv_title = (TextView) this.findViewById(R.id.tv_title);
        ll_indicator= (LinearLayout) this.findViewById(R.id.ll_indicator);
    }

    /**
     * 是否已经滚动
     */
    private boolean isDragging = false;
    /**
     * 上一次高亮显示的位置
     */
    private int prePosition = 0;

    class MyOnPagerChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当页面滚动了的时候回调这个方法
         * @param position 当前页面的位置
         * @param positionOffset 滑动页面的百分比
         * @param positionOffsetPixels 在屏幕上滑动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中了的时候回调
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            int realPosition = position%imageViews.size();
            //设置对应页面的文本信息
            tv_title.setText(imageDescriptions[realPosition]);

            //把上一个高亮的设置默认-灰色
            ll_indicator.getChildAt(prePosition).setEnabled(false);
            //当前的设置为高亮-红色
            ll_indicator.getChildAt(realPosition).setEnabled(true);

            prePosition = realPosition;
        }

        /**
         * 当页面滚动状态变化的时候回调这个方法
         * 状态包括：
         * 静止->滑动
         * 滑动-->静止
         * 静止-->拖拽
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == ViewPager.SCROLL_STATE_DRAGGING){
                isDragging = true;
                handler.removeCallbacksAndMessages(null);
                Log.e(TAG,"SCROLL_STATE_DRAGGING-------------------");
            }else if(state == ViewPager.SCROLL_STATE_SETTLING){
                Log.e(TAG,"SCROLL_STATE_SETTLING-----------------");

            }else if(state == ViewPager.SCROLL_STATE_IDLE&&isDragging){
                isDragging = false;
                Log.e(TAG,"SCROLL_STATE_IDLE------------");
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0,4000);
            }
        }
    }


}
