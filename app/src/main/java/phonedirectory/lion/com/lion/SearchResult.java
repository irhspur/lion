package phonedirectory.lion.com.lion;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import utility.FileParse;
import utility.UtilityString;

/**
 * Created by irhspur on 8/10/14.
 */
public class SearchResult extends ListActivity {

    ListView listView;
    ArrayList<String> result = new ArrayList<String>();
    ArrayList<String> index = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchString = intent.getStringExtra(SearchManager.QUERY); //get query from search view
            viewById();

            InputStreamReader csvStreamReader = null;
            try {
                csvStreamReader = new InputStreamReader(SearchResult.this.getAssets().open("lion.csv"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            result = FileParse.getString(searchString, csvStreamReader, false); //Parse the file

            InputStreamReader csvStreamReader1 = null;
            try {
                csvStreamReader1 = new InputStreamReader(SearchResult.this.getAssets().open("lion.csv"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            index = FileParse.getString(searchString, csvStreamReader1, true);
            final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, result);
            setListAdapter(adapter);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){

        super.onListItemClick(l, v, position, id);
        Class displayClass = null;
        System.out.println("Roop - " + index.get(position));
        try {
            displayClass = Class.forName("phonedirectory.lion.com.lion.Profile");
            Intent intent = new Intent(SearchResult.this, displayClass);
            intent.putExtra("profileId", index.get(position));
            startActivity(intent);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void viewById(){
        listView = (ListView) findViewById(R.id.listVew);
    }

    private class StableArrayAdapter extends ArrayAdapter<String>{
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects){
            super(context, textViewResourceId, objects);

            for(int i = 0; i < objects.size(); ++i){
                mIdMap.put(objects.get(i),i);
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
