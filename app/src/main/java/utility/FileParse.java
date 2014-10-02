package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Created by irhspur on 10/2/14.
 */
public class FileParse {

    public static ArrayList<String> getString(String searchString, InputStreamReader csvStreamReader, boolean isIndex){

        List<String[]> list = new ArrayList<String[]>();
        String next[];
        final ArrayList<String> result = new ArrayList<String>();
        final ArrayList<String> index = new ArrayList<String>();

        try {
           /* InputStreamReader csvStreamReader = new InputStreamReader(new FileInputStream("lion.csv"));*/

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
                index.add(list.get(i)[0]);
            }
        }

        if(isIndex)
            return index;
        else
            return result;
    }

    public static ArrayList<String> getInfoByIndex(InputStreamReader csvStreamReader, String index){

        List<String[]> list = new ArrayList<String[]>();
        String next[]={};
        final ArrayList<String> info = new ArrayList<String>();


        try {
           /* InputStreamReader csvStreamReader = new InputStreamReader(new FileInputStream("lion.csv"));*/

            CSVReader reader = new CSVReader(csvStreamReader);
            for (; ; ) {
                next = reader.readNext();
                if (next != null) {
                    list.add(next);
                } else {
                    break;
                }
                for (int i = 0; i < list.size(); i++)
                    if(list.get(i)[0].equalsIgnoreCase(index)){
                        for(int j = 0; j < next.length; j++)
                            info.add(list.get(i)[j]);
                        break;
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }





        return info;

    }
}

