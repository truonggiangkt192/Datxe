package com.android.datxe.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.android.datxe.R;

/**
 * Created by t430 on 6/12/2017.
 */

public class FontTextView extends TextView {
    public FontTextView(Context context) {
        super(context);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(this, context, attrs);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(this, context, attrs);
    }

    private void setCustomFont(TextView textView, Context context, AttributeSet attrs) {
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomFont);
            String font = a.getString(R.styleable.CustomFont_font);
            setCustomFont(textView, font, context);
            a.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCustomFont(TextView textView, String font, Context context) {
        if(font == null) {
            return;
        }
        Typeface tf = FontCache.get(font, context);
        if(tf != null) {
            textView.setTypeface(tf);
        }
    }
}
