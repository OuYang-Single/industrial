package com.ijcsj.ui_library.widget.keyboard;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.core.content.ContextCompat;

import com.ijcsj.ui_library.R;
import com.ijcsj.ui_library.toolitem.WMToolBold;
import com.ijcsj.ui_library.toolitem.WMToolFallback;
import com.ijcsj.ui_library.toolitem.WMToolImage;
import com.ijcsj.ui_library.toolitem.WMToolImagePhotograph;
import com.ijcsj.ui_library.toolitem.WMToolItalic;
import com.ijcsj.ui_library.toolitem.WMToolReply;
import com.ijcsj.ui_library.toolitem.WMToolTextColor;
import com.ijcsj.ui_library.toolitem.WMToolTextSize;
import com.ijcsj.ui_library.toolitem.WMToolTime;
import com.ijcsj.ui_library.toolitem.WMToolTimes;
import com.ijcsj.ui_library.widget.WMToolContainer;
import com.ijcsj.ui_library.widget.keyboard.emoticon.EmoticonBean;
import com.ijcsj.ui_library.widget.keyboard.emoticon.EmoticonSetBean;
import com.ijcsj.ui_library.widget.keyboard.emoticon.db.EmoticonDBHelper;
import com.ijcsj.ui_library.widget.keyboard.emoticon.util.DefEmoticons;
import com.ijcsj.ui_library.widget.keyboard.emoticon.util.EmoticonHandler;
import com.ijcsj.ui_library.widget.keyboard.emoticon.util.EmoticonsKeyboardBuilder;
import com.ijcsj.ui_library.widget.keyboard.emoticon.view.EmoticonsTabBarView;
import com.ijcsj.ui_library.widget.keyboard.media.MediaBean;
import com.ijcsj.ui_library.widget.keyboard.media.MediaLayout;
import com.ijcsj.ui_library.widget.keyboard.utils.EmoticonBase;
import com.ijcsj.ui_library.widget.keyboard.utils.HadLog;
import com.ijcsj.ui_library.widget.keyboard.utils.Utils;
import com.ijcsj.ui_library.widget.keyboard.view.HadEditText;
import com.ijcsj.ui_library.widget.keyboard.view.SoftHandleLayout;
import com.ijcsj.ui_library.widget.utils.PerformEdit;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;


/**
 * @author chris
 */
public class ChatKeyboardCLayout extends SoftHandleLayout implements EmoticonsTabBarView
        .OnEmoticonsTabChangeListener, WMToolTextColor.ToolTextColor {

    PerformEdit peformEdit;
    public void setPeformEdit(PerformEdit peformEdit) {

        this.peformEdit=peformEdit;
    }

    public static class Style {
        private Style() {
        }

        public static final int NONE = 0;
        public static final int TEXT_ONLY = 1;
        public static final int TEXT_EMOTICON = 2;
        public static final int CHAT_STYLE = 3;
    }

    @IntDef({Style.NONE, Style.TEXT_ONLY, Style.TEXT_EMOTICON, Style.CHAT_STYLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface KeyboardStyle {
    }

    private Drawable mLeftDefaultIcon;
    private Drawable mLeftSecondIcon;
    private int mEmoticonLayoutPos = 0; //display emoticons area
    private int mMediaLayoutPos = 0;    //display medias area
    private int mLayoutOrderCount = 0;
    private int mChildViewPosition = -1;

    private LinearLayout lyBottomLayout;
    private WMToolContainer wmToolContainer;
    private WMToolImagePhotograph wmToolImagePhotograph;
    private WMToolImage mWMToolImage;
    private WMToolFallback mWMToolFallback;
    private WMToolReply mWMToolReply;

    private boolean isShowRightIcon = false;
    private boolean isAddedEmoticonLayout = false;
    @KeyboardStyle
    int mCurrentStyle = Style.NONE;

    public ChatKeyboardCLayout(Context context) {
        super(context, null);
        initView(context);
    }

    public ChatKeyboardCLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable
                .ChatKeyboardLayout);
        Drawable btnSendBg = typedArray.getDrawable(R.styleable
                .ChatKeyboardLayout_sendButtonBackground);
        String btnSendText = typedArray.getString(R.styleable.ChatKeyboardLayout_sendButtonText);
        ColorStateList btnSendTextColor = typedArray.getColorStateList(R.styleable
                .ChatKeyboardLayout_sendButtonTextColor);
        isShowRightIcon = typedArray.getBoolean(R.styleable.ChatKeyboardLayout_showRightIcon,
                false);
        @KeyboardStyle int keyboardStyle = typedArray.getInt(R.styleable
                .ChatKeyboardLayout_keyboardStyle, Style.TEXT_ONLY);
        setKeyboardStyle(keyboardStyle);
        mLeftDefaultIcon = typedArray.getDrawable(R.styleable.ChatKeyboardLayout_leftDefaultIcon);
        mLeftSecondIcon = typedArray.getDrawable(R.styleable.ChatKeyboardLayout_leftSecondIcon);
        Drawable rightIcon = typedArray.getDrawable(R.styleable.ChatKeyboardLayout_rightIcon);
        Drawable faceIcon = typedArray.getDrawable(R.styleable.ChatKeyboardLayout_faceIcon);

        typedArray.recycle();

        /*if (btnSendBg != null) {
            btnSend.setBackgroundDrawable(btnSendBg);
        }*/

        if (mLeftDefaultIcon != null) {
           // leftIconView.setImageDrawable(mLeftDefaultIcon);
        } else {
           // mLeftDefaultIcon = ContextCompat.getDrawable(mContext, R.mipmap.default_record_icon);
        }
        if (mLeftSecondIcon == null) {
            mLeftSecondIcon = ContextCompat.getDrawable(mContext, R.mipmap.default_keyboard_icon);
        }

    }
  private   WMToolTextColor wmToolTextColor;
    private void initView(Context context) {
        // must be in front of inflate
        EmoticonHandler.getInstance(context).loadEmoticonsToMemory();
        LayoutInflater.from(context).inflate(R.layout.keyboard_bar_layout_d, this);
     //   WMToolContainer
        wmToolTextColor=  new WMToolTextColor(this);
        wmToolContainer=    findViewById(R.id.WMToolContainer);
        mWMToolFallback=   new WMToolFallback();
        mWMToolReply=   new WMToolReply();
        wmToolImagePhotograph= new WMToolImagePhotograph(mOnChatKeyBoardListeners);
        mWMToolImage=new WMToolImage(mOnChatKeyBoardListeners);
        wmToolContainer.addToolItem(new WMToolTime());
        wmToolContainer.addToolItem(wmToolImagePhotograph);
        wmToolContainer.addToolItem(mWMToolImage);
        wmToolContainer.addToolItem(new WMToolTimes());
        wmToolContainer.addToolItem(new WMToolBold());
        wmToolContainer.addToolItem(new WMToolItalic());
        wmToolContainer.addToolItem(new WMToolTextSize());
        wmToolContainer.addToolItem(wmToolTextColor);
        wmToolContainer.addToolItem(mWMToolFallback);
        wmToolContainer.addToolItem(mWMToolReply);

        lyBottomLayout = (LinearLayout) findViewById(R.id.view_keyboard_bottom);
        setAutoHeightLayoutView(lyBottomLayout);

    }

    public WMToolTextColor getWmToolTextColor() {
        return wmToolTextColor;
    }

    public void initMediaContents(View view) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lyBottomLayout.addView(view, params);
        mMediaLayoutPos = mLayoutOrderCount;
        mLayoutOrderCount = 1;
    }
    boolean aBoolean=false;
    @Override
    public void onTextColorClick(View v) {
        switch (mKeyboardState) {
            case KEYBOARD_STATE_BOTH:
                closeSoftKeyboard(wmToolContainer.getTools().get(0).getEditText());
                show(mEmoticonLayoutPos);
                aBoolean=true;
           //     leftIconView.setTextColor(getContext().getColor(R.color.keyboard_edit_text_hint_2));
                break;
            case KEYBOARD_STATE_NONE:
            case KEYBOARD_STATE_FUNC:
                aBoolean=false;
              // leftIconView.setTextColor(getContext().getColor(R.color.keyboard_edit_text_hint_1));
                break;
        }
    }


    private void show(int position) {
        int childCount = lyBottomLayout.getChildCount();
        if (position < childCount) {
            for (int i = 0; i < childCount; i++) {
                if (i == position) {
                    lyBottomLayout.getChildAt(i).setVisibility(VISIBLE);
                    mChildViewPosition = i;
                } else {
                    lyBottomLayout.getChildAt(i).setVisibility(GONE);
                }
            }
        }
    }

    public WMToolContainer getWmToolContainer() {
        return wmToolContainer;
    }

    public WMToolFallback getWMToolFallback() {
        return mWMToolFallback;
    }
    public WMToolImagePhotograph getWMToolImagePhotograph() {
        return wmToolImagePhotograph;
    }

    public WMToolImage getWMToolImage() {
        return mWMToolImage;
    }
  public WMToolReply getWMToolReply() {
        return mWMToolReply;
    }

    /**
     * Set the keyboard style, {@link Style}
     * when this method is called, the keyboard will change it's style immediately
     * if the style is same as current style, it won't change
     *
     * @param style style of keyboard
     */
    public void setKeyboardStyle(@KeyboardStyle int style) {
        // when style changed, restore the keyboard to init state
        if (mCurrentStyle == style) {
            return;
        }


    }

    /**
     * show keyboard layout
     */
    public void showLayout() {
        findViewById(R.id.keyboard_layout_id).setVisibility(VISIBLE);
        int barHeight = findViewById(R.id.keyboard_layout_id).getHeight();

    }

    /**
     * judge whether keyboard layout is show
     *
     * @return true or false
     */
    public boolean isLayoutVisible() {
        return VISIBLE == findViewById(R.id.keyboard_layout_id).getVisibility();
    }



    /**
     * judge whether keyboard was popped
     *
     * @return true or false
     */
    public boolean isKeyboardPopped() {
        return mKeyboardState != KEYBOARD_STATE_NONE;
    }

    @Override
    protected void autoViewHeightChanged(final int height) {
        super.autoViewHeightChanged(height);
        if (mOnChatKeyBoardListeners!=null&&lyBottomLayout.getVisibility()==GONE){
            mOnChatKeyBoardListeners.onKeyboardPop(0);
        }
        if (findViewById(R.id.keyboard_layout_id).getVisibility() != VISIBLE) {

        } else {
            int barHeight = findViewById(R.id.keyboard_layout_id).getHeight();

        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
                if (lyBottomLayout != null && lyBottomLayout.isShown()) {
                    hideAutoView();

                    return true;
                } else {
                    return super.dispatchKeyEvent(event);
                }
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    protected void onSoftKeyboardPop(int height) {
        super.onSoftKeyboardPop(height);
        if (mOnChatKeyBoardListeners!=null){
            mOnChatKeyBoardListeners.onKeyboardPop(height);
        }

    }
    private OnChatKeyBoardListeners mOnChatKeyBoardListeners;
    public void setOnChatKeyBoardListeners(OnChatKeyBoardListeners l) {
        this.mOnChatKeyBoardListeners = l;
        mWMToolImage.setOnChatKeyBoardListeners(l);
        wmToolImagePhotograph.setOnChatKeyBoardListeners(l);
    }
    @Override
    protected void onSoftKeyboardClose() {
        super.onSoftKeyboardClose();

        if (mOnChatKeyBoardListeners!=null){
            mOnChatKeyBoardListeners.onSend("");
        }
    }
    public interface OnChatKeyBoardListeners {
        void onSend(String text);
        void onKeyboardPop(int height);
        void onScreenshot();
        void picture();
    }
    public interface OnChatKeyBoardListener {
        /**
         * When send button clicked
         *
         * @param text content in input area
         */
        void onSendButtonClicked(String text);


    }

    public static class SimpleOnChatKeyboardListener implements OnChatKeyBoardListener {
        @Override
        public void onSendButtonClicked(final String text) {
            // This space for rent
        }

    }

    public enum RecordingAction {
        PERMISSION_NOT_GRANTED, // audio permission not granted
        START,    // start recording
        COMPLETE,  // recording end
        CANCELED,  // recording canceled
        READY_CANCEL,   // state which ready to be canceled
        RESTORE     // state which is restored from WILLCANCEL
    }

    @Override
    public void onTabClicked(final int position) {

    }
}