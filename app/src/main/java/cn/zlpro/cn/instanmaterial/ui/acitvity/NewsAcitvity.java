package cn.zlpro.cn.instanmaterial.ui.acitvity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zlpro.cn.instanmaterial.R;
import cn.zlpro.cn.instanmaterial.ui.fragment.FragmentFactory;
import cn.zlpro.cn.instanmaterial.ui.fragment.NewsFragment;

public class NewsAcitvity extends FragmentActivity {
    @Bind(R.id.tl_tabs)
    public TabLayout mTabLayout;
    @Bind(R.id.vp_view)
    public ViewPager mViewPager;


    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private List<View> mViewList = new ArrayList<>();//页卡视图集合
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_acitvity);

        ButterKnife.bind(this);
        
        //卡片标题
        mTitleList.add("新浪");
        mTitleList.add("知乎");
        mTitleList.add("开发者");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        //mTabLayout.setBackgroundColor(Rc);

        mAdapter = new MyPagerAdapter(this.getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }


    /**
     * ViewPager适配器
     */
    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
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


}
