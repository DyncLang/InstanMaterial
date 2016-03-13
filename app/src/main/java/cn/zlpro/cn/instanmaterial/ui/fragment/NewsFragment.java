package cn.zlpro.cn.instanmaterial.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.zlpro.cn.instanmaterial.R;
import cn.zlpro.cn.instanmaterial.model.enter.MEIZI;
import cn.zlpro.cn.instanmaterial.presenter.GankIoPresenter;
import cn.zlpro.cn.instanmaterial.ui.adapter.NewsGanIoAdapter;
import cn.zlpro.cn.instanmaterial.ui.fragment.base.BaseFragment;
import cn.zlpro.cn.instanmaterial.view.GankIoView;

public class NewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, GankIoView {

    String TAG = this.getClass().getSimpleName();
    @Bind(R.id.rv_ganio)
    RecyclerView rvGanio = null;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout = null;

    private List<MEIZI> meiziList;
    private NewsGanIoAdapter mAdapter;
    GankIoPresenter gankIoPresenter;

    boolean isRefresh;

    int page = 1; //请求数据的页数


    @Override
    protected int provideViewLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initPresenter() {
        Log.e(TAG, "initPresenter: ");
        gankIoPresenter = new GankIoPresenter(context, this);
        gankIoPresenter.init();
    }

    @Override
    public void init() {
        //设置妹子的数据
        Log.e(TAG, "init: ");
        if (meiziList == null) {
            meiziList = new ArrayList<>();
        }
        mAdapter = new NewsGanIoAdapter(context, meiziList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvGanio.setLayoutManager(layoutManager);
        rvGanio.setAdapter(mAdapter);

        //设置下拉刷新
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                //设置自动刷新  回掉刷新方法；
                swipeRefreshLayout.setRefreshing(true);
                gankIoPresenter.fetchMeiziData(page);
            }
        });
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        isRefresh = true;
        page = 1;
        gankIoPresenter.fetchMeiziData(page);
    }


    @Override
    public void showProgress() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideProgress() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showNoMoreView() {

    }

    @Override
    public void showErrowView() {
        //加载错误 一般可能是网络错误

    }

    @Override
    public void ShowMeiZiList(List<MEIZI> meiziList) {

        page++;//加到下一页面
        this.meiziList.clear();
        this.meiziList.addAll(meiziList);
        mAdapter.notifyDataSetChanged();
    }


}
