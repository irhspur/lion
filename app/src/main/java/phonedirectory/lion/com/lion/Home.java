package phonedirectory.lion.com.lion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by irhspur on 8/10/14.
 */
public class Home extends Activity {

    Button newsletter, directory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Context context = this;

        viewById();

        newsletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isNetworkConnected()) {
                    Intent openNewsletter = new Intent(context, Newsletter.class);
                    startActivity(openNewsletter);
                }
                else{
                    Toast t = Toast.makeText(getApplicationContext(), "You are not connected to the internet", Toast.LENGTH_LONG);
                    t.show();
                    t.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                }

            }
        });

        directory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openDirectory = new Intent ("android.intent.action.MAINACTIVITY");
                startActivity(openDirectory);
            }
        });
    }

    private void viewById(){
        newsletter = (Button) findViewById(R.id.newsletter);
        directory = (Button) findViewById(R.id.directory);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }

}
