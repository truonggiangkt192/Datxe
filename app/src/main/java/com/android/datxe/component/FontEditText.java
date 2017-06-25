package com.android.datxe.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.android.datxe.R;

/**
 * Created by t430 on 6/12/2017.
 */

public class FontEditText extends EditText {

    public FontEditText(Context context) {
        super(context);
    }

    public FontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(this, context, attrs);
    }

    public FontEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(this, context, attrs);
    }

    private void setCustomFont(EditText editText, Context context, AttributeSet attrs) {
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomFont);
            String font = a.getString(R.styleable.CustomFont_font);
            setCustomFont(editText, font, context);
            a.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCustomFont(EditText editText, String font, Context context) {
        if(font == null) {
            return;
        }
        Typeface tf = FontCache.get(font, context);
        if(tf != null) {
            editText.setTypeface(tf);
        }
    }
}
