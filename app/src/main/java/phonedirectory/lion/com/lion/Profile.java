package phonedirectory.lion.com.lion;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import phonedirectory.lion.com.lion.R;
import utility.FileParse;

public class Profile extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView address = (TextView) findViewById(R.id.address);

        Intent intent = getIntent();
        String index = (String) intent.getExtras().get("profileId");

        TextView name = (TextView) findViewById(R.id.name);

        InputStreamReader csvStreamReader = null;
        try {
            csvStreamReader = new InputStreamReader(Profile.this.getAssets().open("lion.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> test = FileParse.getInfoByIndex(csvStreamReader, index);
        name.setText(test.get(3));
        address.setText(test.get(5));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_search:
                onSearchRequested();
            default:
                break;
        }
        return true;
    }
}
