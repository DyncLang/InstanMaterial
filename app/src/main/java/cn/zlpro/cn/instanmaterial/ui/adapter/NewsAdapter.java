package cn.zlpro.cn.instanmaterial.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.zlpro.cn.instanmaterial.ui.fragment.FragmentFactory;

/**
 * Created by Xiao_Bailong on 2016/3/9.
 */
public class NewsAdapter extends FragmentPagerAdapter {
    String TAG = this.getClass().getSimpleName();


    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private List<View> mViewList = new ArrayList<>();//页卡视图集合


    public NewsAdapter(FragmentManager fm) {
        super(fm);
        //卡片标题
        mTitleList.add("干货");
        mTitleList.add("知乎");
        mTitleList.add("干货");
    }

    @Override
    public Fragment getItem(int position) {
        Log.e(TAG, "getItem: " + position);
        return FragmentFactory.createFragment(position);
    }
    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
