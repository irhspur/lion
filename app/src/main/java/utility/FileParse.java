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

    public static List<String[]> getString(String searchString, InputStreamReader csvStreamReader){

        List<String[]> list = new ArrayList<String[]>();
        String next[];
        final List<String[]> result = new ArrayList<String[]>();

        try {

            CSVReader reader = new CSVReader(csvStreamReader,';');
            for (; ; ) {
                next = reader.readNext();
                if (next != null) {
                    list.add(next);
                } else {
                    break;
                }
            }
            for (int i = 0; i < list.size(); i++) {
                if (UtilityString.caseIgnoredContains(list.get(i)[3], searchString)) {
                    result.add(list.get(i));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}

