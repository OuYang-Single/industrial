package com.ijcsj.ui_library.widget.keyboard.emoticon.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.ijcsj.ui_library.R;
import com.ijcsj.ui_library.widget.keyboard.emoticon.EmoticonBean;
import com.ijcsj.ui_library.widget.keyboard.emoticon.util.EmoticonsKeyboardBuilder;
import com.ijcsj.ui_library.widget.keyboard.view.IndicatorView;

/**
 * @author chris
 */
public class EmoticonLayout extends RelativeLayout implements EmoticonsPageView
        .OnEmoticonsPageViewListener {
    private Context mContext;
    private EmoticonsPageView epvContent;
    private IndicatorView ivIndicator;
    private EmoticonsTabBarView etvToolBar;

    public EmoticonLayout(Context context) {
        super(context);
        mContext = context;
        init(mContext);
    }

    public EmoticonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(mContext);
    }

    public EmoticonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(mContext);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.keyboard_emoticon_layout, this);
        ivIndicator = (IndicatorView) findViewById(R.id.emoticon_indicator_view);
        epvContent = (EmoticonsPageView) findViewById(R.id.emoticon_page_view);
        etvToolBar = (EmoticonsTabBarView) findViewById(R.id.emoticon_page_toolbar);
        epvContent.setOnIndicatorListener(this);
        epvContent.setIViewListener(new EmoticonsAdapter.EmoticonsListener() {
            @Override
            public void onItemClick(EmoticonBean bean) {
                mListener.onEmoticonItemClicked(bean);
            }

            @Override
            public void onPageChangeTo(int position) {
                etvToolBar.setToolBtnSelect(position);
            }
        });
        etvToolBar.setTabChangeListener(new EmoticonsTabBarView
                .OnEmoticonsTabChangeListener() {
            @Override
            public void onTabClicked(final int position) {
                epvContent.setPageSelect(position);
            }
        });
    }

    OnEmoticonListener mListener = null;

    public interface OnEmoticonListener {
        void onEmoticonItemClicked(EmoticonBean bean);
    }

    @Override
    public void emoticonsPageViewCountChanged(int count) {
        ivIndicator.setIndicatorCount(count);
    }

    @Override
    public void moveTo(int position) {
        ivIndicator.moveTo(position);
    }

    @Override
    public void moveBy(int oldPosition, int newPosition) {
        ivIndicator.moveTo(newPosition);
    }

    public void addTabBarItem(@NonNull Drawable icon) {
        if (etvToolBar != null) {
            etvToolBar.addItem(icon);
        }
    }

    public void addFixedView(View view, boolean isRight) {
        if (etvToolBar != null) {
            etvToolBar.addFixedView(view, isRight);
        }
    }

    public void setContents(EmoticonsKeyboardBuilder builder, OnEmoticonListener listener) {
        epvContent.setEmoticonContents(builder);
        etvToolBar.setEmoticonContents(builder);
        mListener = listener;
    }
}
