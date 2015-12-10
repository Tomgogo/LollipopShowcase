package com.leju.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * Created by Tom on 2015/10/22.
 */
public class RoundAngleImageView extends ImageView{
    int roundWidth, roundHeight;
    Paint paint1,paint2;
    TypedArray a;
    public RoundAngleImageView(Context context) {
        super(context);
        init(context,null);
    }

    public RoundAngleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public RoundAngleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            a = context.obtainStyledAttributes(attributeSet,R.styleable.RoundAngleImageView);
            assert a != null;
            roundWidth = a.getDimensionPixelSize(R.styleable.RoundAngleImageView_roundWidth,roundWidth);
            roundHeight = a.getDimensionPixelSize(R.styleable.RoundAngleImageView_roundHeight,roundWidth);
        }else {
            float density = context.getResources().getDisplayMetrics().density;
            roundWidth = (int) (roundWidth*density);
            roundHeight = (int) (roundHeight*density);
        }
        paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint1.setAntiAlias(true);
        paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paint2 = new Paint();
        paint2.setXfermode(null);
        a.recycle();
    }
}
