package cn.zlpro.cn.instanmaterial.presenter.base;

import android.content.Context;

import cn.zlpro.cn.instanmaterial.view.base.IBaseView;

/**
 * Presenter 的基类
 * Created by Xiao_Bailong on 2016/3/9.
 */
public abstract class BasePresenter<T extends IBaseView> {
    public Context context;
    public T view;

    public BasePresenter(Context context, T view) {
        this.context = context;
        this.view = view;
    }

    public void init() {
        view.init();
    }


}
