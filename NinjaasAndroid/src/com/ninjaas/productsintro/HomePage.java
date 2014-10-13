package com.ninjaas.productsintro;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class HomePage extends Activity implements OnClickListener {

	private WebView mWebView;
	private RelativeLayout mRelativeView;
	private RelativeLayout mSearchingLayout;
	private static final String TEL_PREFIX = "tel:";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		mWebView = (WebView) findViewById(R.id.webview);
		mSearchingLayout = (RelativeLayout) findViewById(R.id.searching_layout);
		
		findViewById(R.id.product).setOnClickListener(this);
		findViewById(R.id.team).setOnClickListener(this);
		findViewById(R.id.about).setOnClickListener(this);
		findViewById(R.id.contact).setOnClickListener(this);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("http://ninjaas.com/ninjaasapp");
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				mSearchingLayout.setVisibility(View.VISIBLE);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				mSearchingLayout.setVisibility(View.INVISIBLE);
			}
			@Override
			public boolean shouldOverrideUrlLoading(WebView wv, String url) {
			    if(url.startsWith(TEL_PREFIX)) {
			        Intent intent = new Intent(Intent.ACTION_DIAL);
			        intent.setData(Uri.parse(url));
			        startActivity(intent);
			        return true;
			    }
			    return false;
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.product:
			mWebView.loadUrl("http://ninjaas.com/ninjaasapp/products.html");
			break;
		case R.id.team:
			mWebView.loadUrl("http://ninjaas.com/ninjaasapp/team.html");
			break;
		case R.id.about:
			mWebView.loadUrl("http://ninjaas.com/ninjaasapp/about.html");
			break;
		case R.id.contact:
			mWebView.loadUrl("http://ninjaas.com/ninjaasapp/contact.html");
			break;
		default:
			mWebView.loadUrl("http://ninjaas.com/ninjaasapp/index.html");
			break;
		}

	}

	@Override
	public void onBackPressed() {

		if (mWebView.canGoBack()) {
			mWebView.goBack();
		} else {
			super.onBackPressed();
		}

	}

}
