package com.study.webviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "https://www.naver.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        // javascript를 허용함
        webView.getSettings().setJavaScriptEnabled(true);
        // url을 loading해라
        webView.loadUrl(url);
        //Chrome setting
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClientClass());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(((keyCode)==KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        // shouldOverrideUrlLoading method는 현재 페이지에 url을 읽어 올 수 있음
        // 새창 읽어오기, 특정페이지에 특수한 기능 추가 가능
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}