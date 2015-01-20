package com.david.socialhere.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by davidhodge on 12/13/14.
 */
public class BeastViewPager extends ViewPager {

    private int edgeSize = 16; // default dp

    private int mDefaultEdgeSize;
    private int mEdgeSize;
    float mStartDragX;
    OnSwipeOutListener mListener;

    public BeastViewPager(Context context) {
        super(context);
        init();
    }

    public BeastViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void BeastViewPager(OnSwipeOutListener listener) {
        mListener = listener;
    }

    void init() {
        final float density = getContext().getResources().getDisplayMetrics().density;
        mDefaultEdgeSize = (int) (edgeSize * density);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int measuredWidth = getMeasuredWidth();
        final int maxGutterSize = measuredWidth / 10;
        mEdgeSize = Math.min(maxGutterSize, mDefaultEdgeSize);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //Don't handle events within the left gutter. This fixes an issue with
        // ViewPager briefly intercepting the bezel swipe gesture within a DrawerLayout
        // and starting a drag movement, causing a visual stutter.
        if (ev.getAction() != MotionEvent.ACTION_UP && ev.getX() < mEdgeSize) {
            return false;
        }else{
            mStartDragX = 0;
        }
        return super.onTouchEvent(ev);
    }

    public interface OnSwipeOutListener {
        public void onSwipeOutAtEnd();
    }
}