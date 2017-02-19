package com.gjg.learn.viewpage;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Junguang_Gao on 2016/12/12.
 */
public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<ImageView> imageViews;
    private String[] imageDescriptions;

    private Handler handler=new Handler();

    public MyPagerAdapter(Context context,List<ImageView> imageViews,String[] imageDescriptions){
        this.imageViews=imageViews;
        this.imageDescriptions=imageDescriptions;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        //return imageViews.size();
        return Integer.MAX_VALUE;
    }

    /**
     * 判断view是否是object的一个实例
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    /**
     * 相当于getView
     * @param container ViewPager自身
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //防止数组下标越界
        int realPosition = position%imageViews.size();
        ImageView imageView=imageViews.get(realPosition);
        container.addView(imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //清除消息，使之可以手动滑动
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacksAndMessages(null);
                        handler.sendEmptyMessageDelayed(0,4000);
                        break;
                }
                return false;//不消费事件，让点击事件可以执行
            }
        });
        imageView.setTag(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=(int) v.getTag();
                String text=imageDescriptions[position];
                Toast.makeText(mContext,"text="+text,Toast.LENGTH_LONG).show();
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
