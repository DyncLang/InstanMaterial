package cn.zlpro.cn.instanmaterial.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.zlpro.cn.instanmaterial.R;
import cn.zlpro.cn.instanmaterial.model.enter.MEIZI;
import cn.zlpro.cn.instanmaterial.presenter.GankIoPresenter;
import cn.zlpro.cn.instanmaterial.ui.adapter.NewsGanIoAdapter;
import cn.zlpro.cn.instanmaterial.ui.fragment.base.BaseFragment;
import cn.zlpro.cn.instanmaterial.ui.widget.LMRecyclerView;
import cn.zlpro.cn.instanmaterial.utli.TipsUtil;
import cn.zlpro.cn.instanmaterial.view.GankIoView;

/**
 * Created by Xiao_Bailong on 2016/2/29.
 */
public class GankIoFragment extends BaseFragment<GankIoPresenter> implements SwipeRefreshLayout.OnRefreshListener, GankIoView, LMRecyclerView.LoadMoreListener {
    String TAG = this.getClass().getSimpleName();
    @Bind(R.id.rv_ganio)
    LMRecyclerView rvGanio = null;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout = null;

    private List<MEIZI> meiziList;
    private NewsGanIoAdapter mAdapter;

    boolean isRefresh = true;
    boolean isLoading = true;

    int page = 1; //请求数据的页数


    @Override
    protected int provideViewLayoutId() {
        return R.layout.fragment_ganio;
    }

    @Override
    protected void initPresenter() {
        Log.e(TAG, "initPresenter: ");
        presenter = new GankIoPresenter(context, this);
        presenter.init();
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
        rvGanio.setItemAnimator(new DefaultItemAnimator());
        rvGanio.setLoadMoreListener(this);
        rvGanio.setAdapter(mAdapter);

        //设置下拉刷新
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                //设置自动刷新
                swipeRefreshLayout.setRefreshing(true);
                presenter.fetchMeiziData(page);
            }
        });
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        isRefresh = true;
        isLoading = true;
        page = 1;
        presenter.fetchMeiziData(page);
    }


    @Override
    public void loadMore() {
        Log.e(TAG, "loadMore: " + page);
        if (isLoading) {
            presenter.fetchMeiziData(page);
        }
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
        isLoading = true;
        TipsUtil.showTipWithAction(rvGanio, getString(R.string.load_failed), getString(R.string.retry), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.fetchMeiziData(page);
            }
        });

    }

    @Override
    public void showErrowView() {
        presenter.fetchMeiziData(page);
    }

    @Override
    public void ShowMeiZiList(List<MEIZI> meizis) {
        /**
         * 显示一次 证明就当前页的下一次再显示了。
         */
        page++;
        if (isRefresh) {
            Logger.e(meizis.get(0).toString());
            this.meiziList.clear();
            this.meiziList.addAll(meizis);
            mAdapter.notifyDataSetChanged();
            isRefresh = false;
        } else {
            this.meiziList.addAll(meizis);
            mAdapter.notifyDataSetChanged();
        }
        Logger.e(String.valueOf(meiziList.size()));
    }


}
