package phonedirectory.lion.com.lion;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import phonedirectory.lion.com.lion.R;

public class Newsletter extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter);

        WebView webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "http://lionsclubs325b1.org/slideshow/9181news%20letter-september.pdf";
        webview.loadUrl("https://docs.google.com/viewer?url=" + pdf);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
