package phonedirectory.lion.com.lion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by irhspur on 8/10/14.
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timer = new Thread(){
            public void run(){
                try{
                   sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    Intent openMainActivity = new Intent ("android.intent.action.SPLASH");
                    startActivity(openMainActivity);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
