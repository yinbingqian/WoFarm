package com.lnpdit.woofarm.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 走马灯自定义
 * 
 * @author Administrator
 *
 */
public class FocusedtrueTV extends TextView {

	public FocusedtrueTV(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FocusedtrueTV(Context context) {
		super(context);
	}

	public FocusedtrueTV(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean isFocused() {
		return true;
	}

}
