package cn.zlpro.cn.instanmaterial.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cn.zlpro.cn.instanmaterial.presenter.base.BasePresenter;

/**
 * Created by Xiao_Bailong on 2016/1/17.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    public Context context;
    public T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(provideViewLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initPresenter();

        return view;
    }

    protected abstract int provideViewLayoutId();

    protected abstract void initPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
