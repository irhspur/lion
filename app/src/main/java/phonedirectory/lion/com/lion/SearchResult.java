package phonedirectory.lion.com.lion;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utility.FileParse;
import utility.UtilityString;

/**
 * Created by irhspur on 8/10/14.
 */
public class SearchResult extends ListActivity {

    ListView listView;
    List<String[]> result = new ArrayList<String[]>();
    ArrayList<String> populate = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchString = intent.getStringExtra(SearchManager.QUERY); //get query from search view
            String searchSelection = intent.getStringExtra("KEY");//get post to search
            if(searchSelection == null)
                searchSelection = "secretary";
            viewById();

            InputStreamReader csvStreamReader = null;
            try {
                csvStreamReader = new InputStreamReader(SearchResult.this.getAssets().open(searchSelection + ".csv"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            result = FileParse.getString(searchString, csvStreamReader); //Parse the file to populate List<String[]>

            for (int i=0; i < result.size(); i++) {
                populate.add(result.get(i)[3]); //populate names
            }

            final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, populate); //list out names
            setListAdapter(adapter);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){

        super.onListItemClick(l, v, position, id);
        Class displayClass = null;

        try {
            displayClass = Class.forName("phonedirectory.lion.com.lion.Profile");
            Intent intent = new Intent(SearchResult.this, displayClass);
            intent.putExtra("profileId", result.get(position));
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

}
