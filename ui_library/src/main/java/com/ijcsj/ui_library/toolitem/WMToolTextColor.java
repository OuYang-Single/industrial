package com.ijcsj.ui_library.toolitem;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import com.ijcsj.ui_library.R;
import com.ijcsj.ui_library.utils.WMColor;
import com.ijcsj.ui_library.utils.WMUtil;
import com.ijcsj.ui_library.widget.WMEditText;
import com.ijcsj.ui_library.widget.WMHorizontalScrollView;
import com.ijcsj.ui_library.widget.WMImageButton;

import java.util.ArrayList;
import java.util.List;

public class WMToolTextColor extends WMToolItem implements View.OnClickListener {

    private static final String TAG = "WMToolBackgroundColor";

    private PopupWindow popupView;

    private int textColor = WMColor.BLACK.ColorInt;
    private ToolTextColor toolTextColor;
    public WMToolTextColor(){
    }
    public WMToolTextColor(ToolTextColor toolTextColor){
        this.toolTextColor=toolTextColor;
    }

    public void setStyle(int start, int end) {
        int start_fact = start;
        int end_fact = end;
        Editable s = getEditText().getEditableText();
        ForegroundColorSpan[] styleSpans = s.getSpans(start - 1, end + 1, ForegroundColorSpan.class);
        for (ForegroundColorSpan styleSpan : styleSpans) {
            int spanStart = s.getSpanStart(styleSpan);
            int spanEnd = s.getSpanEnd(styleSpan);
            if (styleSpan.getForegroundColor() == textColor) {
                if (spanStart < start) {
                    start_fact = spanStart;
                }
                if (spanEnd > end) {
                    end_fact = spanEnd;
                }
                if (spanStart != spanEnd) {
                    if (spanStart <= start && spanEnd >= end) {
                        return;
                    } else {
                        s.removeSpan(styleSpan);
                    }
                }
            } else {
                if (spanEnd <= start || spanStart >= end) {

                } else {
                    s.removeSpan(styleSpan);
                    if (spanStart < start) {
                        s.setSpan(new ForegroundColorSpan(styleSpan.getForegroundColor()), spanStart, start, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    if (spanEnd > end) {
                        s.setSpan(new ForegroundColorSpan(styleSpan.getForegroundColor()), end, spanEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }

        }
        s.setSpan(new ForegroundColorSpan(textColor), start_fact, end_fact, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public void setToolTextColor(ToolTextColor toolTextColor) {
        this.toolTextColor = toolTextColor;
    }

    public interface ToolTextColor{
        void onTextColorClick(View v) ;
   }

    @Override
    public void applyStyle(int start, int end) {
        setStyle(start, end);
    }

    @Override
    public List<View> getView(final Context context) {
        WMImageButton imageButton = new WMImageButton(context);

        imageButton.setImageResource(R.mipmap.ic_top);

        view = imageButton;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toolTextColor!=null){
                    toolTextColor.onTextColorClick(v);
                }
            }
        });
        List<View> views = new ArrayList<>();
        views.add(view);
        return views;
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {
        if (null == getEditText()) {
            return;
        }
        WMEditText editText = this.getEditText();
        Editable s = editText.getEditableText();
        if (selStart > 0 && selStart == selEnd) {
            ForegroundColorSpan[] styleSpans = s.getSpans(selStart - 1, selStart, ForegroundColorSpan.class);
            for (ForegroundColorSpan styleSpan : styleSpans) {
                if (s.getSpanStart(styleSpan) != s.getSpanEnd(styleSpan)) {
                    textColor = styleSpan.getForegroundColor();
                }
            }
        } else if (selStart != selEnd) {
            ForegroundColorSpan[] styleSpans = s.getSpans(selStart, selEnd, ForegroundColorSpan.class);
            for (ForegroundColorSpan styleSpan : styleSpans) {

                if (s.getSpanStart(styleSpan) <= selStart
                        && s.getSpanEnd(styleSpan) >= selEnd) {
                    if (s.getSpanStart(styleSpan) != s.getSpanEnd(styleSpan)) {
                        textColor = styleSpan.getForegroundColor();
                    }
                }

            }
        }
        onCheckStateUpdate();
    }

    @Override
    public void onCheckStateUpdate() {
        ((WMImageButton) view).setColorFilter(textColor);
        view.invalidate();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        onCheckStateUpdate();
        WMEditText editText = getEditText();
        int selStart = editText.getSelectionStart();
        int selEnd = editText.getSelectionEnd();
        if (selStart < selEnd) {
            setStyle(selStart, selEnd);
        }
    }

    @Override
    public void onClick(View v) {
        setTextColor(v.getId());
        popupView.dismiss();
    }


}