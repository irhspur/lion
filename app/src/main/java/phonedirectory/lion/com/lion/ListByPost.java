package phonedirectory.lion.com.lion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.ls.LSException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utility.FileParse;

/**
 * Created by irhspur on 10/3/14.
 */
public class ListByPost extends ListFragment {

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.list_post_layout, container, false);
//    }

    ListView listView;
    List<String[]> result = new ArrayList<String[]>();
    ArrayList<String> populate = new ArrayList<String>();

    String post;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Bundle args = getArguments();
        if (args  != null && args.containsKey("Post"))
            post = args.getString("Post");

        InputStreamReader csvStreamReader = null;
        try {
            csvStreamReader = new InputStreamReader(getActivity().getAssets().open(post+".csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = FileParse.getStringByPost(csvStreamReader); //Parse the file to populate List<String[]>

        for (int i=0; i < result.size(); i++) {
            populate.add(result.get(i)[2]); //populate names
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, populate); //list out names
        setListAdapter(adapter);
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
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Class displayClass = null;
        try {
            displayClass = Class.forName("phonedirectory.lion.com.lion.Profile");
            Intent intent = new Intent(getActivity(), displayClass);
            intent.putExtra("profileId", result.get(position));
            startActivity(intent);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
