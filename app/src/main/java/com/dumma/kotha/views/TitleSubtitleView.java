package com.dumma.kotha.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dumma.kotha.R;


public class TitleSubtitleView extends LinearLayout {

    private static final String ARG_SUPERSTATE = "ARG_SUPERSTATE";
    private static final String ARG_TITLE_TEXT = "ARG_TITLE_TEXT";
    private static final String ARG_SUBTITLE_TEXT = "ARG_SUBTITLE_TEXT";

    TextView titleText,subtitleText;

    public TitleSubtitleView(Context context) {
        this(context, null);
    }

    public TitleSubtitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleSubtitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(getContext(), R.layout.titlesubtitle,this);

        titleText =  findViewById(R.id.title_tv);
        subtitleText =  findViewById(R.id.subtitle_tv);

        if (attrs!=null) {
            TypedArray a=getContext().obtainStyledAttributes(attrs,R.styleable.TitleSubtitleView,0,0);
            titleText.setText(a.getString(R.styleable.TitleSubtitleView_titleText));
            subtitleText.setText(a.getString(R.styleable.TitleSubtitleView_subtitleText));
            a.recycle();
        } else {
            titleText.setText("");
            subtitleText.setText("");
        }
    }

    public void setTitleText(String title) {
        titleText.setText(title);
    }

    public void setSubtitleText(String subtitle) {
        subtitleText.setText(subtitle);
        subtitleText.requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle state=new Bundle();
        state.putParcelable(ARG_SUPERSTATE, super.onSaveInstanceState());
        state.putString(ARG_TITLE_TEXT, titleText.getText().toString());
        state.putString(ARG_SUBTITLE_TEXT,titleText.getText().toString());
        return(state);
    }

    @Override
    public void onRestoreInstanceState(Parcelable ss) {
        Bundle state=(Bundle)ss;
        super.onRestoreInstanceState(state.getParcelable(ARG_SUPERSTATE));
        titleText.setText(state.getString(ARG_TITLE_TEXT));
        subtitleText.setText(state.getString(ARG_SUBTITLE_TEXT));
    }
}
