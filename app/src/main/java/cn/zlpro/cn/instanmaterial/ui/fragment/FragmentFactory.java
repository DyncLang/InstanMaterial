package cn.zlpro.cn.instanmaterial.ui.fragment;

import java.util.HashMap;

import cn.zlpro.cn.instanmaterial.ui.fragment.base.BaseFragment;

/**
 * Created by Xiao_Bailong on 2016/1/17.
 */
public class FragmentFactory {
    private static HashMap<Integer, BaseFragment> mFragmeent = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int position) {
        BaseFragment fragment = mFragmeent.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new GankIoFragment();
                    break;
                case 1:
                    fragment = new NewsFragment();
                    break;
                case 2:
                    fragment = new NewsFragment();
                    break;
                default:
                    fragment = new NewsFragment();
                    break;
            }
            mFragmeent.put(position, fragment);
        }
        return fragment;
    }
}
