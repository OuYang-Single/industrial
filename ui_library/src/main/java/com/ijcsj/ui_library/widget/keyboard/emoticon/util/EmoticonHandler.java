package com.ijcsj.ui_library.widget.keyboard.emoticon.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;

import androidx.annotation.NonNull;

import com.ijcsj.ui_library.widget.keyboard.emoticon.EmoticonBean;
import com.ijcsj.ui_library.widget.keyboard.emoticon.db.EmoticonDBHelper;
import com.ijcsj.ui_library.widget.keyboard.utils.EmoticonLoader;
import com.ijcsj.ui_library.widget.keyboard.view.VerticalImageSpan;

import java.util.ArrayList;
import java.util.List;


/**
 * @author chris
 */
public class EmoticonHandler {
    private List<EmoticonBean> mEmoticonBeans = new ArrayList<>();
    private static EmoticonHandler sEmoticonHandler = null;
    private Context mContext;
    private EmoticonDBHelper emoticonDbHelper = null;

    public static EmoticonHandler getInstance(@NonNull Context context) {
        if (sEmoticonHandler == null) {
            sEmoticonHandler = new EmoticonHandler(context.getApplicationContext());
        }
        return sEmoticonHandler;
    }

    private EmoticonHandler(Context context) {
        mContext = context;
        emoticonDbHelper = new EmoticonDBHelper(context);
    }

    public EmoticonDBHelper getEmoticonDbHelper() {
        if (emoticonDbHelper == null) {
            emoticonDbHelper = new EmoticonDBHelper(mContext);
        }
        return emoticonDbHelper;
    }

    public List<EmoticonBean> loadEmoticonsToMemory() {
        mEmoticonBeans = emoticonDbHelper.queryAllEmoticonBeans();
        emoticonDbHelper.cleanup();

        return mEmoticonBeans;
    }

    public String getEmoticonUriByTag(String tag) {
        return emoticonDbHelper.getUriByTag(tag);
    }

    public void setTextFace(String content, Spannable spannable, int start, int size) {
        if (mEmoticonBeans == null) {
            mEmoticonBeans = emoticonDbHelper.queryAllEmoticonBeans();
            emoticonDbHelper.cleanup();
        }
        if (content.length() <= 0) {
            return;
        }
        int keyIndex = start;
        for (EmoticonBean bean : mEmoticonBeans) {
            String key = bean.getTag();
            int keyLength = key.length();
            while (keyIndex >= 0) {
                keyIndex = content.indexOf(key, keyIndex);  //when do not find, get -1
                if (keyIndex < 0) {
                    break;
                }
                Drawable drawable = EmoticonLoader.getInstance(mContext).getDrawable(bean
                        .getIconUri());
                drawable.setBounds(0, 0, size, size);
                VerticalImageSpan imageSpan = new VerticalImageSpan(drawable);
                spannable.setSpan(imageSpan, keyIndex, keyIndex + keyLength, Spannable
                        .SPAN_EXCLUSIVE_EXCLUSIVE);
                keyIndex += keyLength;
            }
            keyIndex = start;
        }
    }
}
