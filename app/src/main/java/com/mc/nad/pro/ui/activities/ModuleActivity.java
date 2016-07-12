package com.mc.nad.pro.ui.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.mc.nad.pro.R;
import com.mc.nad.pro.api.DownloadDocs;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModuleActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_NAME = "name";

    @BindView(R.id.webView)
    WebView webView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        ButterKnife.bind(this);

        // get extras
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString(EXTRA_TITLE);
        String name = bundle.getString(EXTRA_NAME);

        // set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        progressBar.setProgress(0);
        progressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

        // init webView
        initWebView(name);
    }

    private void initWebView(String name) {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        webView.loadUrl(DownloadDocs.getFilePathToWebView(name));

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
