package phonedirectory.lion.com.lion;

import android.app.Notification;
import android.content.ComponentCallbacks;
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

        final Bundle dgTeam = new Bundle();
        dgTeam.putString("Post", "dgTeam");

        final Bundle advisor = new Bundle();
        advisor.putString("Post", "advisor");

        final Bundle committee = new Bundle();
        committee.putString("Post", "committee");

        final Bundle dc = new Bundle();
        dc.putString("Post", "dc");




        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setTitle("All Contacts");

        ActionBar.Tab fragTabSecretary = actionBar.newTab().setText("Secretary");
        ActionBar.Tab fragTabPresident = actionBar.newTab().setText("President");
        ActionBar.Tab fragTabChairperson = actionBar.newTab().setText("Chairperson");
        ActionBar.Tab fragTabDgteam = actionBar.newTab().setText("DG Team");
        ActionBar.Tab fragTabAdvisor = actionBar.newTab().setText("Advisor");
        ActionBar.Tab fragTabCommittee = actionBar.newTab().setText("Committee");
        ActionBar.Tab fragTabDc = actionBar.newTab().setText("District Chairperson");


        Fragment fragmentSecretary = new ListByPost();
        Fragment fragmentPresident = new ListByPost();
        Fragment fragmentChairperson = new ListByPost();
        Fragment fragmentDgteam = new ListByPost();
        Fragment fragmentAdvisor = new ListByPost();
        Fragment fragmentCommittee = new ListByPost();
        Fragment fragmentDC = new ListByPost();

        fragmentSecretary.setArguments(secretary);
        fragmentPresident.setArguments(president);
        fragmentChairperson.setArguments(chairperson);
        fragmentDgteam.setArguments(dgTeam);
        fragmentAdvisor.setArguments(advisor);
        fragmentCommittee.setArguments(committee);
        fragmentDC.setArguments(dc);

        fragTabSecretary.setTabListener(new myTabListener(fragmentSecretary));
        fragTabPresident.setTabListener(new myTabListener(fragmentPresident));
        fragTabChairperson.setTabListener(new myTabListener(fragmentChairperson));
        fragTabDgteam.setTabListener(new myTabListener(fragmentDgteam));
        fragTabAdvisor.setTabListener(new myTabListener(fragmentAdvisor));
        fragTabCommittee.setTabListener(new myTabListener(fragmentCommittee));
        fragTabDc.setTabListener(new myTabListener(fragmentDC));

        actionBar.addTab(fragTabSecretary);
        actionBar.addTab(fragTabPresident);
        actionBar.addTab(fragTabChairperson);
        actionBar.addTab(fragTabDgteam);
        actionBar.addTab(fragTabAdvisor);
        actionBar.addTab(fragTabCommittee);
        actionBar.addTab(fragTabDc);



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
            case R.id.dg_team:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                searchSelection = "dgTeam";
                return true;
            case R.id.advisor:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                searchSelection = "advisor";
                return true;
            case R.id.committee:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                searchSelection = "committee";
                return true;
            case R.id.dc:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                searchSelection = "dc";
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
