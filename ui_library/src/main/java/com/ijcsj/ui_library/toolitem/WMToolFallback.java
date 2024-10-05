package com.ijcsj.ui_library.toolitem;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ijcsj.ui_library.R;
import com.ijcsj.ui_library.span.WMImageSpan;
import com.ijcsj.ui_library.utils.WMUtil;
import com.ijcsj.ui_library.widget.WMImageButton;
import com.ijcsj.ui_library.widget.keyboard.ChatKeyboardCLayout;
import com.ijcsj.ui_library.widget.utils.PerformEdit;

import java.util.ArrayList;
import java.util.List;


public class WMToolFallback extends WMToolItem {
    private static final String TAG = "WMToolImage";

    public static final int ALBUM_CHOOSE = 23;

    private Context context;

    private Fragment fragment;
    PerformEdit performEdit;
    private ChatKeyboardCLayout.OnChatKeyBoardListeners mOnChatKeyBoardListeners;
    public WMToolFallback(){
    }

    public void setPerformEdit(PerformEdit performEdit) {
        this.performEdit = performEdit;
    }

    public WMToolFallback(PerformEdit performEdit){
        this.performEdit=performEdit;
    }
    public void setOnChatKeyBoardListeners(ChatKeyboardCLayout.OnChatKeyBoardListeners l) {
        this.mOnChatKeyBoardListeners = l;
    }
    @Override
    public void applyStyle(int start, int end) {

    }

    @Override
    public List<View> getView(final Context context) {
        this.context = context;
        WMImageButton imageButton = new WMImageButton(context);
        imageButton.setImageResource(R.mipmap.ic_fallback);
        ((AppCompatImageView)imageButton).setImageTintList(ColorStateList.valueOf(context.getColor(R.color._DCDCDC)));
        view = imageButton;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                performEdit.undo();
            }
        });
        List<View> views = new ArrayList<>();
        views.add(view);
        return views;
    }




    @Override
    public void onSelectionChanged(int selStart, int selEnd) {
        if (performEdit.getUndoEmpty()) {
            ((AppCompatImageView)view).setImageTintList(ColorStateList.valueOf(view.getContext().getColor(R.color._DCDCDC)));
        } else {
            ((AppCompatImageView)view).setImageTintList(ColorStateList.valueOf(view.getContext().getColor(R.color.picker_black)));
        }
    }

    @Override
    public void onCheckStateUpdate() {
        if (performEdit.getUndoEmpty()) {
            ((AppCompatImageView)view).setImageTintList(ColorStateList.valueOf(view.getContext().getColor(R.color._DCDCDC)));
        } else {
            ((AppCompatImageView)view).setImageTintList(ColorStateList.valueOf(view.getContext().getColor(R.color.picker_black)));
        }
    }

}