package phonedirectory.lion.com.lion;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import phonedirectory.lion.com.lion.R;

public class Newsletter extends Activity {

    protected FrameLayout placeHolder;
    protected WebView webview;
    protected String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter);

        Intent intent = getIntent();
        url = (String) intent.getExtras().get("url");

        initializeUI();
    }

    protected void initializeUI(){

        placeHolder = (FrameLayout) findViewById(R.id.webViewPlaceHolder);

        if(webview == null) {
            webview = new WebView(this);

//            webview.getSettings().setJavaScriptEnabled(true);


            webview.loadUrl(url);
        }

        placeHolder.addView(webview);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(webview != null) {
            //Remove the webview from the old place holder
            System.out.println("HERER___________");
            placeHolder.removeView(webview);
        }

        System.out.println("HERE______________");
        super.onConfigurationChanged(newConfig);

        setContentView(R.layout.activity_newsletter);

        initializeUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webview.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        webview.restoreState(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
