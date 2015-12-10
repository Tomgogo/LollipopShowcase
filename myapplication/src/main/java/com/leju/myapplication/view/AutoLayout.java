package com.leju.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2015/10/26.
 */
public class AutoLayout extends ViewGroup {
    protected List<List<View>> mAllViews = new ArrayList<List<View>>();
    protected List<Integer> mLineHeight = new ArrayList<Integer>();
    private Handler mHandler;
    boolean first = true;

    public AutoLayout(Context context) {
        super(context);
        Log.e("liang", "AutoLayout------");

    }

    public AutoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("liang", "onMeasure------");
        int width = 0;
        int height = 0;
        int lineWidth = 0;
        int lineHeight = 0;

        int childWidth;
        int childHeight;
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int childSize = getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                if (i == childSize - 1) {
                    width = Math.max(lineWidth, width);
                    height += lineHeight;
                }
                continue;
            }
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            childWidth = child.getMeasuredWidth() + marginLayoutParams.leftMargin +
                    marginLayoutParams.rightMargin;
            childHeight = child.getMeasuredHeight() + marginLayoutParams.topMargin +
                    marginLayoutParams.bottomMargin;

            if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
                width = Math.max(lineWidth, width);
                lineWidth = childWidth;
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);

            }
            if (i == childSize - 1) {
                width = Math.max(lineWidth, width);
                height += lineHeight;
            }
        }
        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width, modeHeight ==
                MeasureSpec.EXACTLY ? sizeHeight : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("liang", "onLayout------");
        mAllViews.clear();
        mLineHeight.clear();

        int width = getWidth();
        int lineHeight = 0;
        int lineWidth = 0;
        int childWidth;
        int childHeight = 0;
        List<View> lineViews = new ArrayList<View>();

        int childSize = getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams
                    .rightMargin;
            childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams
                    .bottomMargin;
            if (lineWidth + childWidth > width - getPaddingLeft() - getPaddingRight()) {
                mLineHeight.add(lineHeight);
                mAllViews.add(lineViews);
                lineHeight = childHeight;
                lineWidth = 0;
                lineViews = new ArrayList<>();
            }
            lineWidth += childWidth;
            lineHeight = Math.max(lineHeight, childHeight);
            lineViews.add(child);
        }
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int sizeViews = mAllViews.size();
        for (int i = 0; i < sizeViews; i++) {
            lineHeight = mLineHeight.get(i);
            lineViews = mAllViews.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int childLeft = left + lp.leftMargin;
                int childRight = childLeft + child.getMeasuredWidth();
                int childTop = top + lp.topMargin;
                int childBottom = childTop + child.getMeasuredHeight();
                child.layout(childLeft, childTop, childRight, childBottom);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("liang", "onDraw------");
//        if (first) {
//            first = false;
//            mHandler.sendEmptyMessage(0);
//        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
