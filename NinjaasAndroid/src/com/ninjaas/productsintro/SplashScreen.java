package com.ninjaas.productsintro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

public class SplashScreen extends Activity implements Callback {

	private Handler mHandler;

	private static int MESSAGE = 1000;

	private static int DELAY = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		mHandler = new Handler(this);
		mHandler.sendEmptyMessageDelayed(MESSAGE, DELAY);
	}

	@Override
	public void onBackPressed() {
		mHandler.removeMessages(MESSAGE);
		super.onBackPressed();
	}

	@Override
	public boolean handleMessage(Message msg) {
		Intent homepage = new Intent(this, HomePage.class);
		startActivity(homepage);
		finish();
		return false;
	}
}
