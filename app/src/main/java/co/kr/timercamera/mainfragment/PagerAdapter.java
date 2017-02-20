package co.kr.timercamera.mainfragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import co.kr.timercamera.util.Constants;

/**
 * Created by si on 2017. 2. 14..
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Context mContext;
    FragmentManager fm;

    public MainFragment1 tab1;

    public PagerAdapter(Context context, FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mContext = context;
        this.fm = fm;
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        FragmentTransaction ft = fm.beginTransaction();
        switch (position) {
            case 0:
                tab1 = new MainFragment1();
                ft.add(tab1, Constants.FRAGMENT1);
                return tab1;
            case 1:
                MainFragment2 tab2 = new MainFragment2();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
