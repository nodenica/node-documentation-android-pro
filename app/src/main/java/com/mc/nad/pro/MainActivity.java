package com.mc.nad.pro;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import java.net.MalformedURLException;

import android.os.Build;

import java.net.URL;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;

import nad.mc.com.nodejsdocumentationfree.R;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    /**
     * The view to show the ad.
     */
    private static Toolbar mToolbar;


    /* Your ad unit id. Replace with your actual ad unit id. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar
        setUpToolbar();

        // WebView Instance
        webView = (WebView) findViewById(R.id.webView);
        // Enable JAvaScript
        webView.getSettings().setJavaScriptEnabled(true);
        // Load home page
        webView.loadUrl("file:///android_asset/en/index.html");

        // Handler webView client
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                getSupportActionBar().setTitle(view.getTitle());

                URL urls;
                try {
                    urls = new URL(webView.getUrl());
                    // get file name from url
                    String fileName = urls.getPath().substring(
                            urls.getPath().lastIndexOf("/") + 1);
                    if (fileName != "index.html") {
                        // show back on ActionBar
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    } else {
                        // hide back on ActionBar
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    }
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

    }

    private void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            // Hide back on ActionBar
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        setToolbarColors(R.color.color_primary, R.color.color_primary_dark);
    }

    public void setToolbarColors(int primaryColor, int primaryDarkColor) {
        mToolbar.setBackgroundColor(getResources().getColor(primaryColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // set colorPrimaryDark
            getWindow().setStatusBarColor(getResources().getColor(primaryDarkColor));
            getWindow().setNavigationBarColor(getResources().getColor(primaryColor));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Called before the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
