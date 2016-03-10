package cn.zlpro.cn.instanmaterial.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.zlpro.cn.instanmaterial.ConfigApi;
import cn.zlpro.cn.instanmaterial.http.OkHttpContnent;
import cn.zlpro.cn.instanmaterial.model.enter.MEIZI;
import cn.zlpro.cn.instanmaterial.presenter.base.BasePresenter;
import cn.zlpro.cn.instanmaterial.view.GankIoView;

/**
 * Created by Xiao_Bailong on 2016/3/9.
 */
public class GankIoPresenter extends BasePresenter<GankIoView> {

    public GankIoPresenter(Context context, GankIoView view) {
        super(context, view);
        /**
         *        这里可以构建模型层的引用
         *        如访问网路，
         *
         */

    }

    //这里获取数据 ， 如果拉取数据成功，
    public void fetchMeiziData(int page) {

        String url = ConfigApi.MEIZI_URL + page;
        OkHttpContnent.getAsyn(url, new OkHttpContnent.ResultCallback<String>() {
            @Override
            public void onErroe(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    Gson gson = new Gson();
                    List<MEIZI> meiziList = new ArrayList<MEIZI>();
                    JSONObject json = new JSONObject(response);
                    JSONArray results = json.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        String result = results.getString(i);
                        MEIZI meizi = gson.fromJson(result, MEIZI.class);
                        meiziList.add(meizi);
                    }
                    //获取成功
                    view.ShowMeiZiList(meiziList);
                    if (meiziList.size() == 0) {
                        view.showProgress();
                    } else {
                        view.hideProgress();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
