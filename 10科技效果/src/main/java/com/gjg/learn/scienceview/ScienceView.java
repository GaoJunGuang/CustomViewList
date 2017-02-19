package com.gjg.learn.scienceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Junguang_Gao on 2016/12/23.
 */
public class ScienceView extends View{
    private char[] words = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'J', 'K', 'L', 'M', 'N', 'O','P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z'
    };
    private Paint mPaint;
    private int row=12;//要显示的行
    private int cloumn=60;//要显示的列
    private Cell[][] cells;

    public ScienceView(Context context) {
        this(context,null);
    }

    public ScienceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScienceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            for (int j = 0; j < row; j++) {

                for (int i = cloumn - 1; i >= 0; i--) {
                    // 1、如果数行透明度为0，则有一定机率变为255
                    // 2、如果中间行透明度为0，不做处理
                    // 3、中间行不为0，依次减少一个梯度
                    // 4、我上面的一个是255，那么我也是255,而他亮度减1
                    Cell cell = cells[j][i];

                    if (j>=0) {
                        if (cell.alpha == 0) {
                            if (Math.random() * 10 > 9) {
                                cell.alpha = 255;
                            }
                        } else {
                            cell.alpha = cell.alpha - 25 > 0 ? cell.alpha - 25
                                    : 0;
                        }
                    } else if (i > 0 && i <= row - 1) {
                        if (cells[j][i - 1].alpha == 255) {
                            cell.alpha = 255;
                        } else {
                            cell.alpha = cell.alpha - 25 > 0 ? cell.alpha - 25
                                    : 0;
                        }
                    }
                }
            }
            invalidate();

        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cloumn; j++) {
                Cell cell = cells[i][j];
                // 小机率事件，改变内容
                if (Math.random() * 100 > 85) {
                    cell.word = ""+ words[(int) (Math.random() * words.length)];
                }
                // 根据透明度确定颜色
                if (cell.alpha >= 250) {
                    mPaint.setColor(Color.WHITE);
                } else {
                    mPaint.setColor(Color.GREEN);
                }
                // 设置透明度
                mPaint.setAlpha(cell.alpha);

                // 绘制
                if (cell.alpha != 0) {
                    //以横向方式画
                    canvas.drawText(cell.word, cell.j * 20+20, (float) (cell.i
                            * 80  + 40), mPaint);
                }
            }
        }
        handler.sendEmptyMessageDelayed(0, 10);

    }

    private void initView() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(30f);
        mPaint.setTextAlign(Paint.Align.CENTER);
        cells=new Cell[row][cloumn];
        //先初始化20行 40列个字母
        for(int i=0;i<row;i++){
            for(int j=0;j<cloumn;j++){
                cells[i][j]=new Cell(i,j);
                //初始化透明度为0
                cells[i][j].alpha= (int) (Math.random()*255);
                cells[i][j].word=words[(int) (Math.random()*words.length)]+"";

            }
        }

    }
    private class Cell {
        /** 行 */
        public int i;
        /** 列 */
        public int j;
        /** 透明度 */
        public int alpha;
        public String word;
        public Cell(int i, int j) {
            super();
            this.i = i;
            this.j = j;
        }



    }
}
