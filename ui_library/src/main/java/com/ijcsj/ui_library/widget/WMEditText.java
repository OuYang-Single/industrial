package com.ijcsj.ui_library.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.MetricAffectingSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;


import com.ijcsj.ui_library.R;
import com.ijcsj.ui_library.bean.MemoryEditBean;
import com.ijcsj.ui_library.span.WMListClickToSwitchSpan;
import com.ijcsj.ui_library.toolitem.RoundedBackgroundSpan;
import com.ijcsj.ui_library.toolitem.WMToolItem;
import com.ijcsj.ui_library.utils.WMHtml;
import com.ijcsj.ui_library.utils.WMImageGetter;
import com.ijcsj.ui_library.utils.WMTagHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WMEditText extends AppCompatEditText {
    private static final String TAG = "WMEditText";

    private List<WMToolItem> tools = new ArrayList<>();

    private boolean editable = true;


    TextWatcher textWatcher = new TextWatcher() {
        int input_start;
        int input_end;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            input_start = start;
            input_end = start + count;

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (editable) {
                if (input_end > input_start) {
                    for (WMToolItem tool : tools) {
                        tool.applyStyle(input_start, input_end);
                    }
                }
            }
        }
    };

    public WMEditText(Context context) {
        this(context, null);
    }

    public WMEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WMEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitView();

    }

    @SuppressLint("NewApi")
    public void InitView() {
        //  this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        // this.setLayerType(LAYER_TYPE_SOFTWARE,null);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.requestFocus();
        this.setBackgroundColor(0);
//        int p = WMUtil.getPixelByDp(getContext(), 25);
//        this.setPadding(p, p, p, p);
//        int space = WMUtil.getPixelByDp(getContext(), 10);
//        this.setLineSpacing(space, 1);
//        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        this.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE
                | EditorInfo.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        addTextChangedListener(textWatcher);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (editable) {
            if (tools != null && tools.size() > 0) {
                for (WMToolItem tool : tools) {
                    tool.onSelectionChanged(selStart, selEnd);
                }
            }
        }
    }



    public void setupWithToolContainer(WMToolContainer toolContainer) {
        this.tools = toolContainer.getTools();
        for (WMToolItem tool : tools) {
            tool.setEditText(this);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flag = false;
        float x = event.getX() - getPaddingLeft();
        float y = event.getY() - getPaddingTop();
        Editable s = getEditableText();
        WMListClickToSwitchSpan[] clickToSwitchSpans = s.getSpans(0, s.length(), WMListClickToSwitchSpan.class);

        for (WMListClickToSwitchSpan clickToSwitchSpan : clickToSwitchSpans) {
            if (clickToSwitchSpan.onTouchEvent(event, x, y)) {
                flag = true;
            }
        }
        invalidate();
        return flag || super.onTouchEvent(event);
    }

    public void fromHtml(String html) {
        fromHtml(html, 0);
    }

    public void fromHtml(String html, int textSizeOffset) {
        boolean current = editable;
        editable = false;
        WMHtml.ImageGetter imageGetter = new WMImageGetter(getContext(), this);
        WMHtml.TagHandler tagHandler = new WMTagHandler();
        Spanned spanned = WMHtml.fromHtml(html, WMHtml.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH, imageGetter, tagHandler, textSizeOffset,new WMEditTexts.TimeClick() {
            @Override
            public void onClick(Long time) {
                Log.w("","");
            }
        });
        if (spanned.length() > 0) {
            ((SpannableStringBuilder) spanned).delete(spanned.length() - 1, spanned.length());
        }
        setText(spanned);
        setStyle(getText().length() );
        editable = current;
    }
    private void setStyle(int end) {

        Editable s =getEditableText();
        MetricAffectingSpan[] styleSpanss = s.getSpans(0, end, MetricAffectingSpan.class);
        for (MetricAffectingSpan styleSpan : styleSpanss) {
            int spanStart = s.getSpanStart(styleSpan);
            int spanEnd = s.getSpanEnd(styleSpan);
            if (styleSpan instanceof RoundedBackgroundSpan) {
                s.setSpan(new AbsoluteSizeSpan(12, true), spanStart, spanEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    public String getHtml() {
        Log.d(TAG, "getHtml: " + getEditableText().length());
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        String editTextHtml = WMHtml.toHtml(getEditableText(), WMHtml.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL);
        html.append(editTextHtml);
        html.append("</body></html>");
        return html.toString();
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        setEnabled(editable);
        setFocusable(editable);
    }

}