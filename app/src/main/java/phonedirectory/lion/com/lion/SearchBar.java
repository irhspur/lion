package phonedirectory.lion.com.lion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import utility.UtilityString;

/**
 * Created by irhspur on 8/10/14.
 */
public class SearchBar extends Activity implements View.OnClickListener{

    Button search;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_bar);
        viewById();

        search.setOnClickListener(this);
    }

    private void viewById(){

        input = (EditText) findViewById(R.id.searchBar);
        search = (Button) findViewById(R.id.searchButton);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.searchButton:
                if(!UtilityString.isNUll(input.getText().toString())){
                    Intent executeSearch = new Intent("android.intent.action.SEARCH_RESULT");
                    executeSearch.putExtra("searchString",input.getText().toString());
                    startActivity(executeSearch);
                }
                else{
                    Toast t = Toast.makeText(getApplicationContext(), "Please enter the text to search", Toast.LENGTH_LONG);
                    t.show();
                    t.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                }
                break;
        }
    }

}
