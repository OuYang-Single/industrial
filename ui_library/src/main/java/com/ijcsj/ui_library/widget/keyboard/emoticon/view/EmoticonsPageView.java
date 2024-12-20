package com.ijcsj.ui_library.widget.keyboard.emoticon.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ijcsj.ui_library.widget.keyboard.emoticon.EmoticonBean;
import com.ijcsj.ui_library.widget.keyboard.emoticon.EmoticonSetBean;
import com.ijcsj.ui_library.widget.keyboard.emoticon.util.EmoticonsKeyboardBuilder;
import com.ijcsj.ui_library.widget.keyboard.utils.Utils;
import com.ijcsj.ui_library.widget.keyboard.view.SoftListenLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * @author chris
 */
public class EmoticonsPageView extends ViewPager implements EmoticonsAdapter.EmoticonsListener {
    private Context mContext;
    private int mPageHeight = 0;
    private int mPageWidth = 0;
    private int mOldPagePosition = -1;
    private List<EmoticonSetBean> mEmoticonSetBeanList;

    public EmoticonsPageView(Context context) {
        this(context, null);
    }

    public EmoticonsPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPageHeight = h;
        mPageWidth = w;
        post(new Runnable() {
            @Override
            public void run() {
                updateView();
            }
        });
    }

    private void updateView() {
        if (mEmoticonSetBeanList == null || mPageHeight <= 0 || mPageWidth <= 0) {
            return;
        }

        List<View> emoticonPageViews = new ArrayList<>();
        clearOnPageChangeListeners();
        addOnPageChangeListener(new PageChangeListener());
        // init to show indicators
        mOnEmoticonsPageViewListener.emoticonsPageViewCountChanged(getPageCount
                (mEmoticonSetBeanList.get(0)));

        for (EmoticonSetBean bean : mEmoticonSetBeanList) {
            List<EmoticonBean> emoticonList = bean.getEmoticonList();
            if (emoticonList != null) {
                int emoticonSetSum = emoticonList.size();
                int row = bean.getRow();
                int line = bean.getLine();

                int everyPageMaxSum = row * line - (bean.isShowDelBtn() ? 1 : 0);
                int pageCount = getPageCount(bean);

                int start = 0;
                int end = everyPageMaxSum > emoticonSetSum ? emoticonSetSum : everyPageMaxSum;

                RelativeLayout.LayoutParams gridParams = new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams
                                .WRAP_CONTENT);
                gridParams.addRule(SoftListenLayout.CENTER_VERTICAL);

                int horizontalSpacing = Utils.dip2px(mContext, bean.getHorizontalSpacing());
                int verticalSpacing = Utils.dip2px(mContext, bean.getVerticalSpacing());
                int itemHeight = Math.min(
                        (mPageWidth - getPaddingRight() - getPaddingLeft() -
                                (bean.getRow() - 1) * horizontalSpacing) / bean.getRow(),
                        (mPageHeight - getPaddingTop() - getPaddingBottom() -
                                (bean.getLine() - 1) * verticalSpacing) / bean.getLine());

                for (int i = 0; i < pageCount; i++) {
                    RelativeLayout rl = new RelativeLayout(mContext);
                    GridView gridView = new GridView(mContext);
                    gridView.setNumColumns(bean.getRow());
                    gridView.setBackgroundColor(Color.TRANSPARENT);
                    gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
                    gridView.setCacheColorHint(0);
                    gridView.setHorizontalSpacing(horizontalSpacing);
                    gridView.setVerticalSpacing(verticalSpacing);
                    gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
                    gridView.setGravity(Gravity.CENTER);
                    gridView.setVerticalScrollBarEnabled(false);

                    List<EmoticonBean> list = new ArrayList<>();
                    for (int j = start; j < end; j++) {
                        list.add(emoticonList.get(j));
                    }

                    if (bean.isShowDelBtn()) {
                        int count = bean.getLine() * bean.getRow();
                        while (list.size() < count - 1) {
                            list.add(null);
                        }
                        list.add(new EmoticonBean(EmoticonBean.FACE_TYPE_DEL,
                                "drawable://icon_del", null, null));
                    } else {
                        int count = bean.getLine() * bean.getRow();
                        while (list.size() < count) {
                            list.add(null);
                        }
                    }

                    EmoticonsAdapter adapter = new EmoticonsAdapter(mContext, list, bean
                            .isShownName());
                    adapter.setHeight(itemHeight, Utils.dip2px(mContext, bean.getItemPadding()));
                    gridView.setAdapter(adapter);
                    rl.addView(gridView, gridParams);
                    emoticonPageViews.add(rl);
                    adapter.setOnItemListener(this);

                    start = everyPageMaxSum + i * everyPageMaxSum;
                    end = everyPageMaxSum + (i + 1) * everyPageMaxSum;
                    if (end >= emoticonSetSum) {
                        end = emoticonSetSum;
                    }
                }
            }
        }
        setAdapter(new EmoticonsViewPagerAdapter(emoticonPageViews));
    }

    public void setPageSelect(int position) {
        if (getAdapter() != null && position >= 0 && position < mEmoticonSetBeanList.size()) {
            int count = 0;
            for (int i = 0; i < position; i++) {
                count += getPageCount(mEmoticonSetBeanList.get(i));
            }
            setCurrentItem(count);
        }
    }

    private class PageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // unused for now
        }

        @Override
        public void onPageSelected(int position) {
            if (mOldPagePosition < 0) {
                mOldPagePosition = 0;
            }
            int end = 0;
            int pagerPosition = 0;
            for (EmoticonSetBean emoticonSetBean : mEmoticonSetBeanList) {
                int size = getPageCount(emoticonSetBean);
                if (end + size > position) {
                    mOnEmoticonsPageViewListener.emoticonsPageViewCountChanged(size);
                    if (mOldPagePosition - end >= size) {
                        if (position - end >= 0) {
                            mOnEmoticonsPageViewListener.moveTo(position - end);
                        }
                        if (mIViewListeners != null && !mIViewListeners.isEmpty()) {
                            for (EmoticonsAdapter.EmoticonsListener listener : mIViewListeners) {
                                listener.onPageChangeTo(pagerPosition);
                            }
                        }
                        break;
                    }
                    if (mOldPagePosition - end < 0) {
                        mOnEmoticonsPageViewListener.moveTo(0);
                        if (mIViewListeners != null && !mIViewListeners.isEmpty()) {
                            for (EmoticonsAdapter.EmoticonsListener listener : mIViewListeners) {
                                listener.onPageChangeTo(pagerPosition);
                            }
                        }
                        break;
                    }
                    mOnEmoticonsPageViewListener.moveBy(mOldPagePosition - end, position - end);
                    break;
                }
                pagerPosition++;
                end += size;
            }
            mOldPagePosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            // unused for now
        }
    }

    public int getPageCount(EmoticonSetBean emoticonSetBean) {
        int pageCount = 0;
        if (emoticonSetBean != null && emoticonSetBean.getEmoticonList() != null) {
            int everyPageMaxSum = emoticonSetBean.getRow() * emoticonSetBean.getLine() -
                    (emoticonSetBean.isShowDelBtn() ? 1 : 0);
            pageCount = (int) Math.ceil((double) emoticonSetBean.getEmoticonList().size() /
                    everyPageMaxSum);
        }
        return pageCount;
    }

    public void setEmoticonContents(EmoticonsKeyboardBuilder builder) {
        mEmoticonSetBeanList = builder.builder.getEmoticonSetBeanList();
    }

    private class EmoticonsViewPagerAdapter extends PagerAdapter {
        private List<View> mEmoticonPageViews;

        public EmoticonsViewPagerAdapter(List<View> pageViews) {
            mEmoticonPageViews = pageViews;
        }

        @Override
        public int getCount() {
            return mEmoticonPageViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mEmoticonPageViews.get(position));
            return mEmoticonPageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

    @Override
    public void onItemClick(EmoticonBean bean) {
        if (mIViewListeners != null && !mIViewListeners.isEmpty()) {
            for (EmoticonsAdapter.EmoticonsListener listener : mIViewListeners) {
                listener.onItemClick(bean);
            }
        }
    }

    @Override
    public void onPageChangeTo(int position) {

    }

    private List<EmoticonsAdapter.EmoticonsListener> mIViewListeners;

    public void addIViewListener(EmoticonsAdapter.EmoticonsListener listener) {
        if (mIViewListeners == null) {
            mIViewListeners = new ArrayList<>();
        }
        mIViewListeners.add(listener);
    }

    public void setIViewListener(EmoticonsAdapter.EmoticonsListener listener) {
        addIViewListener(listener);
    }

    private OnEmoticonsPageViewListener mOnEmoticonsPageViewListener;

    public void setOnIndicatorListener(OnEmoticonsPageViewListener listener) {
        mOnEmoticonsPageViewListener = listener;
    }

    public interface OnEmoticonsPageViewListener {
        void emoticonsPageViewCountChanged(int count);

        void moveTo(int position);

        void moveBy(int oldPosition, int newPosition);
    }
}