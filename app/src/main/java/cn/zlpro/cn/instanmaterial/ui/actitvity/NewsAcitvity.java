package cn.zlpro.cn.instanmaterial.ui.actitvity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zlpro.cn.instanmaterial.R;
import cn.zlpro.cn.instanmaterial.ui.adapter.NewsAdapter;

public class NewsAcitvity extends AppCompatActivity {
    String TAG = "NewsActivity";
    @Bind(R.id.tl_tabs)
    public TabLayout mTabLayout;
    @Bind(R.id.vp_view)
    public ViewPager mViewPager;


    private LayoutInflater mInflater;

    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_acitvity);


        ButterKnife.bind(this);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        //mTabLayout.setBackgroundColor(Rc);

        mAdapter = new NewsAdapter(this.getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }


}
