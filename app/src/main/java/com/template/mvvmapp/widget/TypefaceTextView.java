package com.template.mvvmapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Checkable;

import androidx.appcompat.widget.AppCompatTextView;

import com.template.mvvmapp.R;

/**
 * 自定义字体textview
 */
public class TypefaceTextView extends AppCompatTextView implements Checkable {
    private boolean mChecked;

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    public TypefaceTextView(Context context) {
        this(context, null);
    }

    public TypefaceTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypefaceTextView(context, attrs);
    }

    private void initTypefaceTextView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TypefaceTextView);
        String type = typedArray.getString(R.styleable.TypefaceTextView_typeface);
        if (null == type) {
            return;
        }
        Typeface typeface = null;
        switch (type) {
            case "Alegreya":
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Alegreya-Extra.ttf");
                setTypeface(typeface);
                break;
            case "AlegreyaBold":
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Alegreya-ExtraBold.ttf");
                setTypeface(typeface);
                break;
            case "systemDefault":
                setTypeface(Typeface.DEFAULT);
                break;
        }
        typedArray.recycle();
        typeface = null;
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }


    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
        refreshDrawableState();
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        mChecked = !mChecked;
    }
}
