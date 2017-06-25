package com.android.datxe.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.android.datxe.R;

/**
 * Created by t430 on 6/12/2017.
 */

public class ClearEditText extends EditText {

    Drawable crossx, nonecrossx, drawable;
    Boolean visible = false;

    public ClearEditText(Context context) {
        super(context);
        Initialisation();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Initialisation();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Initialisation();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Initialisation();
    }
    private void Initialisation(){
        crossx = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        nonecrossx = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();

        Configure();
    }

    private void Configure(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible? crossx:nonecrossx;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(MotionEvent.ACTION_DOWN == event.getAction() && event.getX() >= (getRight() - drawable.getBounds().width())){
            setText("");
        }
        return super.onTouchEvent(event);
    }
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if(lengthAfter == 0 && start == 0){
            visible = false;
            Configure();
        }else{
            visible = true;
            Configure();
        }

    }
}
