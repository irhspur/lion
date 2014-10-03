package phonedirectory.lion.com.lion;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewParent;
import android.widget.Toast;

import utility.UtilityString;


public class MainActivity extends ActionBarActivity{

    protected String searchSelection ;
//    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Bundle secretary = new Bundle();
        secretary.putString("Post", "secretary");

        final Bundle president = new Bundle();
        president.putString("Post", "president");

        final Bundle chairperson = new Bundle();
        chairperson.putString("Post", "chairperson");



        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setTitle("All Contacts");

        ActionBar.Tab frag1tab = actionBar.newTab().setText("Secretary");
        ActionBar.Tab frag2tab = actionBar.newTab().setText("President");
        ActionBar.Tab frag3tab = actionBar.newTab().setText("Chairperson");
        Fragment fragment1 = new ListByPost();
        Fragment fragment2 = new ListByPost();
        Fragment fragment3 = new ListByPost();

        fragment1.setArguments(secretary);
        fragment2.setArguments(president);
        fragment3.setArguments(chairperson);

        frag1tab.setTabListener(new myTabListener(fragment1));
        frag2tab.setTabListener(new myTabListener(fragment2));
        frag3tab.setTabListener(new myTabListener(fragment3));

        actionBar.addTab(frag1tab);
        actionBar.addTab(frag2tab);
        actionBar.addTab(frag3tab);


//        viewPager = (ViewPager) findViewById(R.id.pager);
//        PagerAdapter pageAdapter= new PagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(pageAdapter);

    }

    class myTabListener implements ActionBar.TabListener{

        public Fragment fragment;

        myTabListener(Fragment fragment){
            this.fragment = fragment;
        }
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            fragmentTransaction.replace(R.id.textView, fragment);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        // Configure the search info and add any event listeners
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_search:
               onSearchRequested();
                break;
            case R.id.secratary:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                searchSelection = "secretary";
                return true;
            case R.id.president:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                searchSelection = "president";
                return true;
            case R.id.z_chairman:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                searchSelection = "chairperson";
                return true;
            default:
                break;
        }

        return true;
    }

    @Override
    public void startActivity(Intent intent) {
        // check if search intent
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            intent.putExtra("KEY", searchSelection);
        }

        super.startActivity(intent);
    }
}
