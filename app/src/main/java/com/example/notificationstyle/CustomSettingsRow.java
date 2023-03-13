package com.example.notificationstyle;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomSettingsRow extends RelativeLayout {
    private TextView leftTextView;
    private TextView rightTextView;
    private CheckBox checkBox;

    public CustomSettingsRow(Context context) {
        super(context);
    }

    public CustomSettingsRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context,attrs);
    }

    public void initData(Context context,AttributeSet attrs){
        View view=View.inflate(context,R.layout.customs,this);
        leftTextView=(TextView) view.findViewById(R.id.items);
        rightTextView=(TextView) view.findViewById(R.id.value);
        checkBox=view.findViewById(R.id.checkbox);
        checkBox.setVisibility(View.GONE);
        rightTextView.setVisibility(View.GONE);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CustomSettingsRow);
        String showType=typedArray.getString(R.styleable.CustomSettingsRow_showtype);
        switch (showType){
            case "textview":
                rightTextView.setVisibility(View.VISIBLE);
                break;
            case "checkbox":
                checkBox.setVisibility(View.VISIBLE);
                break;
        }
    }
}
