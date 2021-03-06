package com.leju.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.MimeTypeMap;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Tom on 2015/10/23.
 */
public class LargeImageView extends View {
    private BitmapRegionDecoder mDecoder;
    private int mImageWidth,mImageHeight;
    private volatile Rect mRect = new Rect();
    private MoveGestureDetector mDetector;

    private final static BitmapFactory.Options options = new BitmapFactory.Options();
    static {
        options.inPreferredConfig = Bitmap.Config.RGB_565;
    }
    public LargeImageView(Context context) {
        super(context);
    }

    public void setInputStream(InputStream inputStream){
        try {
            mDecoder = BitmapRegionDecoder.newInstance(inputStream,false);
            BitmapFactory.Options tmOptions = new BitmapFactory.Options();
            tmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, tmOptions);
            mImageWidth = tmOptions.outWidth;
            mImageHeight = tmOptions.outHeight;
            requestLayout();
            invalidate();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public LargeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDetector = new MoveGestureDetector(getContext(),new MoveGestureDetector
                .SimpleMoveGestureDetector(){
            @Override
            public boolean onMove(MoveGestureDetector detector) {
                int moveX = (int) detector.getMoveX();
                int moveY = (int) detector.getMoveY();
                if(mImageWidth>getWidth()){
                    mRect.offset(-moveX,0);
                    checkWidth();
                    invalidate();
                }
                if(mImageHeight>getHeight()){
                    mRect.offset(0,-moveY);
                    checkHeight();
                }
                return  true;
            }
        });
    }

    private void checkHeight() {
        Rect rect = mRect;
        int imageHeight = mImageHeight;
        if(rect.bottom > mImageHeight){
            rect.bottom = mImageHeight;
            rect.top = mImageHeight - getHeight();
        }
        if(rect.top<0){
            rect.top = 0;
            rect.bottom = mImageHeight;
        }
    }

    private void checkWidth() {
        Rect rect = mRect;
        int imageWidth = mImageWidth;

        if(rect.right >imageWidth){
            rect.right = imageWidth;
            rect.left = imageWidth - getWidth();
        }
        if(rect.left<0){
            rect.left = 0;
            rect.right = imageWidth;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onToucEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = mDecoder.decodeRegion(mRect, options);
        canvas.drawBitmap(bitmap,0,0,null);
    }
}
