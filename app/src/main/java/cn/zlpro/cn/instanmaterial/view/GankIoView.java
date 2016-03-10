package cn.zlpro.cn.instanmaterial.view;

import java.util.List;

import cn.zlpro.cn.instanmaterial.model.enter.MEIZI;
import cn.zlpro.cn.instanmaterial.view.base.IBaseView;

/**
 * 干货 Viwe
 * Created by Xiao_Bailong on 2016/3/9.
 */
public interface GankIoView extends IBaseView {

    void showProgress();
    void hideProgress();

    void showErrowView();

    /**
     * 控制View层显示的接口
     *
     * @param meiziList
     */
    void ShowMeiZiList(List<MEIZI> meiziList);

}
