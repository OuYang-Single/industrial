package com.ijcsj.ui_library.toolitem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ijcsj.ui_library.R;
import com.ijcsj.ui_library.span.WMImageSpan;
import com.ijcsj.ui_library.utils.ScreenUtils;
import com.ijcsj.ui_library.utils.WMUtil;
import com.ijcsj.ui_library.widget.WMEditText;
import com.ijcsj.ui_library.widget.WMImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WMToolTime extends WMToolItem {
    private static final String TAG = "WMToolImage";

    public static final int ALBUM_CHOOSE = 23;

    private Context context;

    private Fragment fragment;


    @Override
    public void applyStyle(int start, int end) {

    }

    @Override
    public List<View> getView(final Context context) {
        this.context = context;
        WMImageButton imageButton = new WMImageButton(context);
        imageButton.setImageResource(R.mipmap.ic_flag);
        view = imageButton;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            /* long videoTime=   getEditText().getDetailPlayer().getCurrentPositionWhenPlaying();*/
                //insertImage(R.mipmap.ic_flag_1, WMImageSpan.ImageType.RES,convertMilliSecondToHMS(videoTime),videoTime );
            }
        });
        List<View> views = new ArrayList<>();
        views.add(view);
        return views;
    }

    public static String convertMilliSecondToHMS(long milliseconds) {
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        milliseconds -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


    @Override
    public void onSelectionChanged(int selStart, int selEnd) {

    }

    @Override
    public void onCheckStateUpdate() {

    }

    public void insertImage(final Object src, final WMImageSpan.ImageType type,String timeString,long time) {

        SimpleTarget myTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bitmap, Transition<? super Bitmap> transition) {
                if (bitmap == null) {
                    return;
                }

                int sWidth = WMUtil.getScreenWidthAndHeight(context)[0] - getEditText().getPaddingLeft() - getEditText().getPaddingRight();
                bitmap=  Bitmap.createBitmap(bitmap, 0, 0, ScreenUtils.dp2PxInt(context,4), ScreenUtils.dp2PxInt(context,5));
              //  bitmap = WMUtil.scaleBitmapToFitWidth(bitmap, ScreenUtils.dp2PxInt(context,8));

                ImageSpan imageSpan = null;
                if (type == WMImageSpan.ImageType.URI) {
                    imageSpan = new WMImageSpan(context, bitmap, ((Uri) src));
                } else if (type == WMImageSpan.ImageType.URL) {
                    imageSpan = new WMImageSpan(context, bitmap, ((String) src));
                }
                if (imageSpan == null) {
                    return;
                }
                insertSpan(imageSpan,timeString,time);
            }
        };

        if (type == WMImageSpan.ImageType.URI) {
            Glide.with(context).asBitmap().load((Uri) src).centerCrop().into(myTarget);
        } else if (type == WMImageSpan.ImageType.URL) {
            Glide.with(context).asBitmap().load((String) src).centerCrop().into(myTarget);
        } else if (type == WMImageSpan.ImageType.RES) {
            ImageSpan imageSpan = new WMImageSpan(context, ((int) src));
            insertSpan(imageSpan,timeString,time);
        }
    }
    public SpannableStringBuilder getRoundBackgroundWithImageAndText(String text, int imageResourceId, int backgroundColor, int radius) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        Drawable drawable = ContextCompat.getDrawable(context, imageResourceId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        builder.setSpan(imageSpan, 1, 5 ,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        builder.setSpan(new RoundBackgroundSpan(backgroundColor, radius), 0, text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return builder;
    }
    private void insertSpan(ImageSpan imageSpan,String timeString,long time) {
        Editable editable = this.getEditText().getEditableText();
        int start = this.getEditText().getSelectionStart();
        int end = this.getEditText().getSelectionEnd();

        String htmlContent = "<div time=\""+time+"\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;"+timeString+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>";
        Spanned spanned = Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_LEGACY);

        SpannableStringBuilder text = new SpannableStringBuilder("");
        text.append("\n");
        text.append("\n");
        text.append(spanned);
        text.append("\n");
        RoundedBackgroundSpan  span= new RoundedBackgroundSpan(ContextCompat.getColor(context,R.color.colorB6B6B6), ContextCompat.getColor(context,R.color._2C2C2C),context,imageSpan.getDrawable(),time);
      //  text.setSpan(new AbsoluteSizeSpan( ScreenUtils.dp2PxInt(context,12)) ,0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(span ,1, text.length()-2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(clickableSpan,1, text.length()-2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        editable.replace(start, end, text);
       editable.setSpan(new AbsoluteSizeSpan(12, true), start, end+text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    ClickableSpan clickableSpan= new ClickableSpan() {
        @Override
        public void onClick(@NonNull View view) {

            Log.w(TAG,"ClickableSpan  onClick");
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(true);
        }
    };
    public void setupWithFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}