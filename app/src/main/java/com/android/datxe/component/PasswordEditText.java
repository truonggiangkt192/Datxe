package com.android.datxe.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.android.datxe.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by t430 on 6/12/2017.
 */

public class PasswordEditText extends EditText {

    Drawable eye,eyeStrike;
    Boolean visible = false;
    Boolean useStrike = false;
    Boolean useValidate = false;
    Drawable drawable;
    int ALPHA = (int) (255 * .70f);
    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})"; // (?=.*\d)
    Pattern pattern;
    Matcher matcher;

    public PasswordEditText(Context context) {
        super(context);
        Initialisation(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Initialisation(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Initialisation(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Initialisation(attrs);
    }
    private void Initialisation(AttributeSet attrs){

        this.pattern = Pattern.compile(MATCHER_PATTERN);
        if(attrs != null){
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.PasswordEditText,0,0);
            this.useStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike,false);
            this.useValidate = array.getBoolean(R.styleable.PasswordEditText_useValidate,false);
        }
        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();

        if(this.useValidate){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b) {
                        String chuoi = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) view.getParent();
                        matcher = pattern.matcher(chuoi);
                        if(!matcher.matches()){
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mật khẩu phải bao gồm 6 ký tự và một chữ hoa");
                        }else{
                            textInputLayout.setErrorEnabled(false);
                            textInputLayout.setError("");
                        }


                    }
                }
            });
        }
        Install();
    }

    private void Install(){
        setInputType(InputType.TYPE_CLASS_TEXT |(visible? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && !visible? eyeStrike : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width()) ){
            visible = !visible;
            Install();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
