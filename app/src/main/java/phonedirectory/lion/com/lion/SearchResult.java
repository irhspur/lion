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

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import utility.UtilityString;

/**
 * Created by irhspur on 8/10/14.
 */
public class SearchResult extends Activity /*implements View.OnClickListener*/{

    private TextView result[] = new TextView[6];
    /*Button search;
    EditText input;*/
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchString = intent.getStringExtra(SearchManager.QUERY);
            int resultIndex = 0;
            Toast t = Toast.makeText(getApplicationContext(), searchString, Toast.LENGTH_LONG);
            t.show();
            t.setGravity(Gravity.BOTTOM, 0, 0);

            viewById();

            /*search.setOnClickListener(this);*/

            List<String[]> list = new ArrayList<String[]>();
            String next[];
            final ArrayList<String> result = new ArrayList<String>();

            try {
                InputStreamReader csvStreamReader = new InputStreamReader(SearchResult.this.getAssets().open("lion.csv"));

                CSVReader reader = new CSVReader(csvStreamReader);
                for (; ; ) {
                    next = reader.readNext();
                    if (next != null) {
                        list.add(next);
                    } else {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            for (int i = 0; i < list.size(); i++) {
                if (UtilityString.caseIgnoredContains(list.get(i)[3], searchString)) {
                    result.add(list.get(i)[3]);
                }
            }
            final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, result);
            listView.setAdapter(adapter);
        }
    }

    private void viewById(){
      /*  search = (Button) findViewById(R.id.searchButton);
        input = (EditText) findViewById(R.id.searchBar);*/
        listView = (ListView) findViewById(R.id.listVew);
    }
/*
    @Override
    public void onClick(View view){

        if(!UtilityString.isNUll(input.getText().toString())){
            Intent executeSearch = new Intent ("android.intent.action.SEARCH_RESULT");
            executeSearch.putExtra("searchString",input.getText().toString());
            startActivity(executeSearch);
        }
        else{
            Toast t = Toast.makeText(getApplicationContext(), "Please enter the text to search", Toast.LENGTH_LONG);
            t.show();
            t.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
       }

    }*/

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
