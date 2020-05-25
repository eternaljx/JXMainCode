package com.smartseat.workgroup.common.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.smartseat.workgroup.R;

public class LoadingDialog extends Dialog {

	private TextView mTextView;

	public LoadingDialog(Context context) {
		super(context);
	}

	public LoadingDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.loading_dialog);
		this.setCanceledOnTouchOutside(false);
		mTextView = findViewById(R.id.loading_text);
	}

	public void updateStatusText(String text) {
		this.mTextView.setText(text);
	}

}
