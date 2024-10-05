package com.ijcsj.ui_library.widget.keyboard;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ijcsj.ui_library.R;
import com.ijcsj.ui_library.widget.keyboard.emoticon.EmoticonBean;
import com.ijcsj.ui_library.widget.keyboard.emoticon.EmoticonSetBean;
import com.ijcsj.ui_library.widget.keyboard.emoticon.db.EmoticonDBHelper;
import com.ijcsj.ui_library.widget.keyboard.emoticon.util.DefEmoticons;
import com.ijcsj.ui_library.widget.keyboard.emoticon.util.EmoticonHandler;
import com.ijcsj.ui_library.widget.keyboard.emoticon.util.EmoticonsKeyboardBuilder;
import com.ijcsj.ui_library.widget.keyboard.emoticon.view.EmoticonLayout;
import com.ijcsj.ui_library.widget.keyboard.emoticon.view.EmoticonsTabBarView;
import com.ijcsj.ui_library.widget.keyboard.media.MediaBean;
import com.ijcsj.ui_library.widget.keyboard.media.MediaLayout;
import com.ijcsj.ui_library.widget.keyboard.utils.EmoticonBase;
import com.ijcsj.ui_library.widget.keyboard.utils.HadLog;
import com.ijcsj.ui_library.widget.keyboard.utils.Utils;
import com.ijcsj.ui_library.widget.keyboard.view.HadEditText;
import com.ijcsj.ui_library.widget.keyboard.view.SoftHandleLayout;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chris
 */
public class ChatKeyboardLayout extends SoftHandleLayout implements EmoticonsTabBarView
        .OnEmoticonsTabChangeListener {
    public static class Style {
        private Style() {
        }

        public static final int NONE = 0;
        public static final int TEXT_ONLY = 1;
        public static final int TEXT_EMOTICON = 2;
        public static final int CHAT_STYLE = 3;
    }

    @IntDef({Style.NONE,Style.TEXT_ONLY, Style.TEXT_EMOTICON, Style.CHAT_STYLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface KeyboardStyle {
    }

    private Drawable mLeftDefaultIcon;
    private Drawable mLeftSecondIcon;
    private int mEmoticonLayoutPos = 0; //display emoticons area
    private int mMediaLayoutPos = 0;    //display medias area
    private int mLayoutOrderCount = 0;
    private int mChildViewPosition = -1;
    private HadEditText etInputArea;
    private ViewGroup rlInput;
    private LinearLayout lyBottomLayout;

    private ImageView btnSend;

    private TextView leftIconView;
    private boolean isShowRightIcon = false;
    private boolean isAddedEmoticonLayout = false;
    @KeyboardStyle
    int mCurrentStyle = Style.NONE;

    public ChatKeyboardLayout(Context context) {
        super(context, null);
        initView(context);
    }

    public ChatKeyboardLayout(Context context, AttributeSet attrs) {
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

    private void initView(Context context) {
        // must be in front of inflate
        EmoticonHandler.getInstance(context).loadEmoticonsToMemory();
        LayoutInflater.from(context).inflate(R.layout.keyboard_bar_layout, this);

        rlInput = (ViewGroup) findViewById(R.id.view_keyboard_input_layout);
        lyBottomLayout = (LinearLayout) findViewById(R.id.view_keyboard_bottom);

        leftIconView = (TextView) findViewById(R.id.view_keyboard_left_icon);


        btnSend = (ImageView) findViewById(R.id.view_keyboard_send_button);

        etInputArea = (HadEditText) findViewById(R.id.et_chat);

        setAutoHeightLayoutView(lyBottomLayout);
        leftIconView.setOnClickListener(new FaceClickListener());

        btnSend.setOnClickListener(new SendClickListener());


        etInputArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                aBoolean=false;
                leftIconView.setTextColor(getContext().getColor(R.color.keyboard_edit_text_hint_1));
                if (!etInputArea.isFocused()) {
                    etInputArea.setFocusable(true);
                    etInputArea.setFocusableInTouchMode(true);
                }
                return false;
            }
        });


        etInputArea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {

                    setEditableState(true);
                } else {
                    setEditableState(false);
                }
            }
        });
        etInputArea.setOnTextChangedInterface(new HadEditText.OnTextChangedInterface() {
            @Override
            public void onTextChanged(CharSequence arg0) {
                String str = arg0.toString();
                if (mOnChatKeyBoardListener != null) {
                 //   mOnChatKeyBoardListener.onInputTextChanged(str);
                }
                if (!isShowRightIcon) {
                    return;
                }
                if (TextUtils.isEmpty(str)) {

                } else {
                    aBoolean=false;
                    leftIconView.setTextColor(getContext().getColor(R.color.keyboard_edit_text_hint_1));
                }
            }
        });
    }

    public void setEditableState(boolean editableState) {
        if (editableState) {
            etInputArea.setFocusable(true);
            etInputArea.setFocusableInTouchMode(true);
            etInputArea.requestFocus();
        } else {
            etInputArea.setFocusable(false);
            etInputArea.setFocusableInTouchMode(false);
        }
    }
    public void setEditableStates(boolean editableState) {
        if (editableState) {
            etInputArea.setFocusable(true);
            etInputArea.setFocusableInTouchMode(true);
            etInputArea.requestFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(etInputArea, InputMethodManager.SHOW_IMPLICIT);
        } else {
            etInputArea.setFocusable(false);
            etInputArea.setFocusableInTouchMode(false);
        }
    }


    public ImageView getSendButton() {
        return btnSend;
    }

    public HadEditText getInputEditText() {
        return etInputArea;
    }

    public void clearInputContent() {
        etInputArea.setText("");
    }

    public void del() {
        int action = KeyEvent.ACTION_DOWN;
        int code = KeyEvent.KEYCODE_DEL;
        KeyEvent event = new KeyEvent(action, code);
        etInputArea.onKeyDown(KeyEvent.KEYCODE_DEL, event);
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
        clearInputContent();
        etInputArea.setVisibility(VISIBLE);

        hideKeyboard();
        switch (style) {
            case Style.TEXT_ONLY:
             //   btnSend.setVisibility(VISIBLE);
             //   leftIconView.setVisibility(GONE);
                isShowRightIcon = false;
                break;
            case Style.TEXT_EMOTICON:
                showEmoticons();
                btnSend.setVisibility(VISIBLE);
             //   leftIconView.setVisibility(GONE);
                isShowRightIcon = false;
                break;
            case Style.CHAT_STYLE:
                showEmoticons();
                if (isShowRightIcon) {
                 //   btnSend.setVisibility(GONE);
                } else {
                  //  btnSend.setVisibility(VISIBLE);
                }

                leftIconView.setVisibility(VISIBLE);
                break;
        }
        mCurrentStyle = style;
    }

    /**
     * when keyboard style was set as Style.CHAT_STYLE
     * you can set right icon behavior, this method will clear the input content
     *
     * @param isShow true, right icon will show, and when user input text, it will be hidden;
     *               false, right icon won't show in any situation
     */
    public void setShowRightIcon(boolean isShow) {
        clearInputContent();
        isShowRightIcon = isShow;
        if (isShowRightIcon) {
            btnSend.setVisibility(GONE);
        } else {
            btnSend.setVisibility(VISIBLE);
        }
    }

    /**
     * hide whole layout of keyboard
     */
    public void hideLayout() {
        hideKeyboard();
        findViewById(R.id.keyboard_layout_id).setVisibility(GONE);
    }

    /**
     * show keyboard layout
     */
    public void showLayout() {
        findViewById(R.id.keyboard_layout_id).setVisibility(VISIBLE);
        int barHeight = findViewById(R.id.keyboard_layout_id).getHeight();
        if (mOnChatKeyBoardListener != null) {
         //   mOnChatKeyBoardListener.onKeyboardHeightChanged(barHeight);
        }
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
     * hide soft keyboard
     */
    public void hideKeyboard() {
        hideAutoView();

        closeSoftKeyboard(etInputArea);
    }

    /**
     * pop soft keyboard, if the layout is hidden, it will show layout first
     */
    public void popKeyboard() {
        showLayout();
        openSoftKeyboard(etInputArea);
        showAutoView();
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
        if (findViewById(R.id.keyboard_layout_id).getVisibility() != VISIBLE) {
            if (mOnChatKeyBoardListener != null) {
            //    mOnChatKeyBoardListener.onKeyboardHeightChanged(0);
            }
        } else {
            int barHeight = findViewById(R.id.keyboard_layout_id).getHeight();
            if (mOnChatKeyBoardListener != null) {
              //  mOnChatKeyBoardListener.onKeyboardHeightChanged(barHeight);
            }
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

    /**
     * The action after left icon clicked
     * default action is to show or hide input area and recording bar
     *
     * @param view view
     */
    protected void leftIconClicked(View view) {
        if (rlInput.isShown()) {
            // switch to voice recording bar
            hideAutoView();
            closeSoftKeyboard(etInputArea);
            rlInput.setVisibility(INVISIBLE);
            aBoolean=false;
            leftIconView.setTextColor(getContext().getColor(R.color.keyboard_edit_text_hint_1));
          //  leftIconView.setImageDrawable(mLeftSecondIcon);
            btnSend.setVisibility(GONE);
            if (isShowRightIcon) {
            }
        } else {
            // switch to text input bar
            rlInput.setVisibility(VISIBLE);
            setEditableState(true);
            openSoftKeyboard(etInputArea);

           // leftIconView.setImageDrawable(mLeftDefaultIcon);
            if (!TextUtils.isEmpty(etInputArea.getText().toString())) {
                btnSend.setVisibility(VISIBLE);
            }
            if (!isShowRightIcon) {    //if media button not be shown, show button send
                // every time
                btnSend.setVisibility(VISIBLE);
            }
        }
    }

    /**
     * The action after right icon clicked
     * default action is show media layout, you can implement it by yourself
     *
     * @param view view
     */
    protected void rightIconClicked(View view) {
        switch (mKeyboardState) {
            case KEYBOARD_STATE_BOTH:
                closeSoftKeyboard(etInputArea);
                show(mMediaLayoutPos);
                break;
            case KEYBOARD_STATE_NONE:

                rlInput.setVisibility(VISIBLE);
               // leftIconView.setImageDrawable(mLeftDefaultIcon);
                setEditableState(true);
                showAutoView();
                show(mMediaLayoutPos);
                break;
            case KEYBOARD_STATE_FUNC:

                if (mChildViewPosition == mMediaLayoutPos) {
                    openSoftKeyboard(etInputArea);
                } else {
                    show(mMediaLayoutPos);
                }
                break;
        }
    }

    private class SendClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            if (mOnChatKeyBoardListener != null) {
                mOnChatKeyBoardListener.onSendButtonClicked(etInputArea.getText().toString());
            }
        }
    }


boolean aBoolean=false;
    private class FaceClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            switch (mKeyboardState) {
                case KEYBOARD_STATE_BOTH:
                    closeSoftKeyboard(etInputArea);
                    show(mEmoticonLayoutPos);
                    aBoolean=true;
                    leftIconView.setTextColor(getContext().getColor(R.color.keyboard_edit_text_hint_2));
                    break;
                case KEYBOARD_STATE_NONE:
                case KEYBOARD_STATE_FUNC:
                    aBoolean=false;
                    leftIconView.setTextColor(getContext().getColor(R.color.keyboard_edit_text_hint_1));
                    setEditableStates(true);
                    break;
            }
        }
    }



    /**
     * init media contents, if you need media area
     *
     * @param mediaContents media contents
     */
    public void initMediaContents(List<MediaBean> mediaContents) {
        MediaLayout mediaLayout = new MediaLayout(mContext);
        mediaLayout.setContents(mediaContents);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lyBottomLayout.addView(mediaLayout, params);
        mMediaLayoutPos = mLayoutOrderCount;
        mLayoutOrderCount = 1;
    }
    public void initMediaContents(View view) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lyBottomLayout.addView(view, params);
        mMediaLayoutPos = mLayoutOrderCount;
        mLayoutOrderCount = 1;
    }
    private void addEmoticonLayout() {
        if (isAddedEmoticonLayout) {
            return;
        }

        mEmoticonLayoutPos = mLayoutOrderCount;
        mLayoutOrderCount = 1;
        isAddedEmoticonLayout = true;
    }

    private void showEmoticons() {
        addEmoticonLayout();
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

    private OnChatKeyBoardListener mOnChatKeyBoardListener;
    private OnChatKeyBoardListeners mOnChatKeyBoardListeners;

    public void setOnChatKeyBoardListener(OnChatKeyBoardListener l) {
        this.mOnChatKeyBoardListener = l;
    }
    public void setOnChatKeyBoardListeners(OnChatKeyBoardListeners l) {
        this.mOnChatKeyBoardListeners = l;
    }
    /**
     * judge whether emoticons db init success, you can just just init db, when it's not success
     *
     * @param context context
     * @return true or false
     */
    public static boolean isEmoticonsDBInitSuccess(Context context) {
        return Utils.isInitDb(context);
    }

    /**
     * init emoticons db
     *
     * @param context          context
     * @param isShowEmoji      is show default emoji
     * @param emoticonEntities user defined emoticons or stickers
     */
    public static void initEmoticonsDB(final Context context, final boolean isShowEmoji, final
    List<EmoticonEntity> emoticonEntities) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EmoticonDBHelper emoticonDbHelper = EmoticonHandler.getInstance(context)
                        .getEmoticonDbHelper();
                if (isShowEmoji) {
                    List<EmoticonBean> emojiArray = Utils.parseData(DefEmoticons.emojiArray,
                            EmoticonBean.FACE_TYPE_NORMAL, EmoticonBase.Scheme.DRAWABLE);
                    EmoticonSetBean emojiEmoticonSetBean = new EmoticonSetBean("emoji", 3, 7);
                    emojiEmoticonSetBean.setIconUri("drawable://icon_emoji");
                    emojiEmoticonSetBean.setItemPadding(25);
                    emojiEmoticonSetBean.setVerticalSpacing(10);
                    emojiEmoticonSetBean.setShowDelBtn(true);
                    emojiEmoticonSetBean.setEmoticonList(emojiArray);
                    emoticonDbHelper.insertEmoticonSet(emojiEmoticonSetBean);
                }

                if (emoticonEntities == null || emoticonEntities.isEmpty()) {
                    Utils.setIsInitDb(context, true);
                    return;
                }
                List<EmoticonSetBean> emoticonSetBeans = new ArrayList<>();
                for (EmoticonEntity entity : emoticonEntities) {
                    try {
                        EmoticonSetBean bean = Utils.ParseEmoticons(context, entity.getPath(),
                                entity.getScheme());
                        emoticonSetBeans.add(bean);
                    } catch (IOException e) {
                        HadLog.e(String.format("read %s config.xml error", entity.getPath()), e);
                    } catch (XmlPullParserException e) {
                        HadLog.e(String.format("parse %s config.xml error", entity.getPath()), e);
                    }
                }

                for (EmoticonSetBean setBean : emoticonSetBeans) {
                    emoticonDbHelper.insertEmoticonSet(setBean);
                }
                emoticonDbHelper.cleanup();

                if (emoticonSetBeans.size() == emoticonEntities.size()) {
                    Utils.setIsInitDb(context, true);
                }
            }
        }).start();
    }

    private EmoticonsKeyboardBuilder getBuilder(Context context) {
        if (context == null) {
            throw new NullPointerException("Context is null, cannot create db helper");
        }
        EmoticonDBHelper emoticonDbHelper = new EmoticonDBHelper(context);
        List<EmoticonSetBean> mEmoticonSetBeanList = emoticonDbHelper.queryAllEmoticonSet();
        emoticonDbHelper.cleanup();

        return new EmoticonsKeyboardBuilder.Builder()
                .setEmoticonSetBeanList(mEmoticonSetBeanList)
                .build();
    }

    @Override
    protected void onSoftKeyboardPop(int height) {
        super.onSoftKeyboardPop(height);


    }

    @Override
    protected void onSoftKeyboardClose() {
        super.onSoftKeyboardClose();
        if (!aBoolean){
            if (mOnChatKeyBoardListeners!=null){
                mOnChatKeyBoardListeners.onSend("");
            }
            leftIconView.setTextColor(getContext().getColor(R.color.keyboard_edit_text_hint_1));
        }
    }
    public interface OnChatKeyBoardListeners {
        void onSend(String text);
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