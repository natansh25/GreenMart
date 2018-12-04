package com.example.greendao.allsignin;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.greendao.allsignin.Fragment.DairyProduct;
import com.example.greendao.allsignin.Fragment.FruitFragment;
import com.example.greendao.allsignin.Fragment.VegetableFragment;
import com.example.greendao.allsignin.Fragment.WafferFragment;

class SimpleFragmentPageAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPageAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override

    public Fragment getItem(int position) {

        if (position == 0) {
            return new VegetableFragment();
        } else if (position == 1){
            return new FruitFragment();
        } else if (position == 2){
            return new WafferFragment();
        } else {
            return new DairyProduct();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
//Problem is with url new url i created data is more
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.category_usefulinfo);
            case 1:
                return mContext.getString(R.string.category_places);
            case 2:
                return mContext.getString(R.string.category_food);
            case 3:
                return mContext.getString(R.string.category_nature);
            default:
                return null;
        }
    }
}