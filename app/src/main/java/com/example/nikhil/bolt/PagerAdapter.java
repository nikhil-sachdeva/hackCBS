package com.example.nikhil.bolt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

public class PagerAdapter extends FragmentStatePagerAdapter {
    Fragment fragment=null;
    public PagerAdapter(FragmentManager fm) {
        super(fm);



    }

    @Override
    public Fragment getItem(int i) {

        if(i==0) {
            fragment = PostFragment.newInstance();
        }
        if(i==1){
            fragment= DangerPredictionFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }


}
