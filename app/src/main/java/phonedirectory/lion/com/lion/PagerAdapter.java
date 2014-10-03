package phonedirectory.lion.com.lion;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by irhspur on 10/3/14.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){

            case 0:
                return new ListByPost();
            case 1:
                return new ListByPost();
            default:
                break;
        }
        return null;
    }


    @Override
    public int getCount() {
        return 2;
    }
}
