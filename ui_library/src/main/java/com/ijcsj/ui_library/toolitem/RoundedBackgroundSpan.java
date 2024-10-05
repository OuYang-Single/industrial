package com.ijcsj.ui_library.toolitem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;
import android.util.Log;

import com.ijcsj.ui_library.utils.ScreenUtils;

/**
 * @author Tobin
 */
public class RoundedBackgroundSpan  extends ReplacementSpan {
    private Context context;
    private static int CORNER_RADIUS = 9;
    private int backgroundColor = 0;
    private int textColor = 0;
    private   Drawable drawable;
    private long time;
    public RoundedBackgroundSpan(int backgroundColor, int textColor, Context context, Drawable drawable,long time) {
        super();
        this.backgroundColor = backgroundColor;
        CORNER_RADIUS= (int) ScreenUtils.dp2Px(context,18);
        this.textColor = textColor;
        this.context = context;
        this.drawable = drawable;
        this.time = time;

    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
//        RectF rect = new RectF(x, top, x + measureText(paint, text, start, end), bottom);
        // fm.bottom - fm.top 解决设置行距（android:lineSpacingMultiplier="1.2"）时背景色高度问题
        Drawable b = drawable;
        canvas.save();
        int transY = bottom - b.getBounds().bottom;
       /* if (mVerticalAlignment == ALIGN_BASELINE) {
            transY -= paint.getFontMetricsInt().descent;
        } else if (mVerticalAlignment == ALIGN_CENTER) {ɪㅣɪ|

        }*/


        int h=0;
        int s=0;
        int d=0;
      int size=  Math.round(paint.measureText(text, start, end));
      if ((ScreenUtils.dp2PxInt(context, 18)-size)>ScreenUtils.dp2PxInt(context, 1)){
          h=(ScreenUtils.dp2PxInt(context, 18)-size)/2;
      }
      if (x>0){
          h= (int) (( ScreenUtils.dp2PxInt(context, 12)) /2);
          s= ScreenUtils.dp2PxInt(context, 2);
          d= ScreenUtils.dp2PxInt(context, 1);
      }

        RectF rect = new RectF(x+ScreenUtils.dp2Px(context,5), top- ScreenUtils.dp2Px(context,8)+h, x + measureText(paint, text, start, end)+ScreenUtils.dp2Px(context,5),bottom+ ScreenUtils.dp2Px(context,8)-h );
        paint.setColor(backgroundColor);

        canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, paint);
        canvas.translate(x+(ScreenUtils.dp2Px(context,18)),   top+(( (float) ScreenUtils.dp2PxInt(context, 12)) /2)+s+d);
        b.draw(canvas);
        canvas.restore();
        paint.setColor(textColor);

        canvas.drawText(text, start, end, x, y-h+s, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm){
        return ScreenUtils.dp2PxInt(context,18);
    }

    private float measureText(Paint paint, CharSequence text, int start, int end) {
        return ScreenUtils.dp2PxInt(context,100);
    }
}