package uk.ac.stir.cs.unit_converter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

public class PageAdapter extends FragmentStatePagerAdapter {

    private final int mNumOfTabs;
    private Map<Integer, String> mFragmentTags;
    private FragmentManager mFragmentManager;


    /**
     * Constructor method
     * @param fragmentManager
     * @param numOfTabs
     */
    public PageAdapter(FragmentManager fragmentManager, int numOfTabs){

        super(fragmentManager);
        this.mFragmentManager = fragmentManager;
        this.mNumOfTabs = numOfTabs;
        mFragmentTags = new HashMap<Integer, String>();
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0: return new unitSelctionFragment();
            case 1: return new UnitConverterFragment();
            default: return null;
        }
    }

    @Override
    public int getCount(){

        return mNumOfTabs;
    }

}
