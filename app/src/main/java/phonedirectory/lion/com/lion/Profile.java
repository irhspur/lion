package phonedirectory.lion.com.lion;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import phonedirectory.lion.com.lion.R;
import utility.FileParse;
import utility.UtilityString;

public class Profile extends ActionBarActivity {

    TextView name, address, phone, club, blood, email, mobile, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String[] index = (String[]) intent.getExtras().get("profileId");

        viewById();

        if(UtilityString.isNotNull(index[5]))
            phone.setText(index[5]);
        if(UtilityString.isNotNull(index[1]))
            club.setText(index[1]);
        if(UtilityString.isNotNull(index[2]))
            name.setText(index[2]);
        if(UtilityString.isNotNull(index[4]))
            address.setText(index[4]);
        if(UtilityString.isNotNull(index[7]))
            email.setText(index[7]);
        if(UtilityString.isNotNull(index[8]))
            blood.setText(index[8]);
        if(UtilityString.isNotNull(index[6]))
            mobile.setText(index[6]);
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

    private void viewById(){
        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        club = (TextView) findViewById(R.id.club);
        blood = (TextView) findViewById(R.id.blood);
        email = (TextView) findViewById(R.id.email);
        mobile = (TextView) findViewById(R.id.mobile);
    }

    @Override
    public void onPause(){
        super.onPause();
        finish();
    }
}
