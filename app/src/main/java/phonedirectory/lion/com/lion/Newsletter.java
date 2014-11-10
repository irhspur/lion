package phonedirectory.lion.com.lion;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter);

        initializeUI();
    }

    protected void initializeUI(){

        placeHolder = (FrameLayout) findViewById(R.id.webViewPlaceHolder);

        if(webview == null) {
            webview = new WebView(this);

            webview.getSettings().setJavaScriptEnabled(true);

            String pdf = "http://lionsclubs325b1.org/slideshow/9181news%20letter-september.pdf";
            webview.loadUrl("https://docs.google.com/viewer?url=" + pdf);
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
