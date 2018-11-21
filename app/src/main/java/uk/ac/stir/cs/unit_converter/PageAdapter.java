package uk.ac.stir.cs.unit_converter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class PageAdapter extends FragmentStatePagerAdapter {

    private final int mNumOfTabs;
    private FragmentManager mFragmentManager;


    /**
     * Constructor method
     *
     * @param fragmentManager
     * @param numOfTabs
     */
    public PageAdapter(FragmentManager fragmentManager, int numOfTabs){

        super(fragmentManager);
        this.mFragmentManager = fragmentManager;
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0: return new UnitSelectionFragment();
            case 1: return new UnitConverterFragment();
            default: return null;
        }
    }

    @Override
    public int getCount(){

        return mNumOfTabs;
    }

    /**
     * Method to start a new instance of the desired fragment
     *
     * @param index         The index of the fragment
     * @param viewPager     This is the ViewPager used to display the fragment
     * @return
     */
    public Fragment getFragment(int index, ViewPager viewPager){


        return (Fragment) instantiateItem(viewPager, index);
    }

}
