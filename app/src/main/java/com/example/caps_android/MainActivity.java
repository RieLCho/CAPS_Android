package com.example.caps_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings webViewSetting;
    private String webUrlLocal = "https://caps.dongguk.edu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.mainWebview);
        webViewSetting = webView.getSettings();

        webViewSetting.setJavaScriptEnabled(true);
        webViewSetting.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient(){});

        webView.loadUrl(webUrlLocal);
    }
    private  final long FINISH_INTERNAL_TIME = 2000;
    private  long backPressedTime = 0;

    @Override
    public void onBackPressed() { //webView 뒤로가기버튼

        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if(webView.canGoBack()){
            if(webView.getUrl().equals("https://caps.dongguk.edu")){
                if (0 <= intervalTime && FINISH_INTERNAL_TIME >= intervalTime) finish();

                else {
                    backPressedTime = tempTime;
                    Toast.makeText(getApplicationContext(), "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
                }
            }
            else webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}