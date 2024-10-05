package com.ijcsj.ui_library.widget;

import static io.reactivex.internal.util.HalfSerializer.onComplete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.MetricAffectingSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;

import com.ijcsj.ui_library.span.WMListClickToSwitchSpan;
import com.ijcsj.ui_library.toolitem.RoundedBackgroundSpan;
import com.ijcsj.ui_library.toolitem.WMToolItem;
import com.ijcsj.ui_library.utils.WMHtml;
import com.ijcsj.ui_library.utils.WMImageGetter;
import com.ijcsj.ui_library.utils.WMTagHandler;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class WMEditTexts extends androidx.appcompat.widget.AppCompatTextView {
    private static final String TAG = "WMEditText";

    private List<WMToolItem> tools = new ArrayList<>();

    private boolean editable = true;



    public WMEditTexts(Context context) {
        this(context, null);
    }

    public WMEditTexts(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WMEditTexts(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitView();

    }

    @SuppressLint("NewApi")
    public void InitView() {
        //  this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        // this.setLayerType(LAYER_TYPE_SOFTWARE,null);


//        int p = WMUtil.getPixelByDp(getContext(), 25);
//        this.setPadding(p, p, p, p);
//        int space = WMUtil.getPixelByDp(getContext(), 10);
//        this.setLineSpacing(space, 1);
//        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        this.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE
                | EditorInfo.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
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


   private TimeClick timeClick;

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
        Observable.just(html).flatMap(new Function<String, ObservableSource<Spanned>>() {
            @Override
            public ObservableSource<Spanned> apply(String s) throws Exception {
                Spanned spanned = WMHtml.fromHtml(html, WMHtml.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH, imageGetter, tagHandler, textSizeOffset,timeClick);
                if (spanned.length() > 0) {
                    ((SpannableStringBuilder) spanned).delete(spanned.length() - 1, spanned.length());
                }
                return Observable.just(spanned);
            }
        }).subscribeOn(Schedulers.io()) //上游的线程
                .observeOn(AndroidSchedulers.mainThread()) //下游的线程
                .subscribe(new Observer<Spanned>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Spanned o) {
                        setText(o);
                        setStyle(getText().length() );
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
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

    public void setTimeClick(TimeClick timeClick) {
        this.timeClick = timeClick;
    }

    public interface  TimeClick{
        void onClick(Long time);
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