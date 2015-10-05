package teamvoy.com.nytandroid.ui;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import teamvoy.com.nytandroid.R;

public class ContentActivity extends AppCompatActivity {
    private String url;
    private WebView mWeb;
    private ProgressBar progress;
    private String TAG=getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent = getIntent();

        url = intent.getStringExtra("url");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);

        //setting the color of up arrow
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //setting actionbar

        getSupportActionBar().setTitle(intent.getStringExtra("section"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting progressbar
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setMax(100);

        //WebView
        mWeb = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWeb.setWebChromeClient(new MyWebChromeClient());
        Log.d(TAG, "onCreate URL="+url);
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(url);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            Log.d(TAG, "onOptionsItemSelected URL=" + url);

            mWeb.stopLoading();
            setValue(0);
            mWeb.loadUrl(url);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class CustomWebViewClient extends WebViewClient {
        private String currentUrl;

        public CustomWebViewClient(String currentUrl) {
            this.currentUrl = currentUrl;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

           /* if (url.equals(currentUrl)) {
                Log.d(TAG, "shouldOverrideUrlLoading = true");
                view.loadUrl(url);
            }
            Log.d(TAG, "shouldOverrideUrlLoading = false");
            */
            return true;
        }

    }
    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            setValue(newProgress);

            super.onProgressChanged(view, newProgress);
        }
    }
    public void setValue(int progress) {
        if(progress==100) this.progress.setVisibility(View.GONE);
        if(progress==0) this.progress.setVisibility(View.VISIBLE);
        this.progress.setProgress(progress);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
