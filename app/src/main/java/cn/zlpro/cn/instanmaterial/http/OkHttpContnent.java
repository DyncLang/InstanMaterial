package cn.zlpro.cn.instanmaterial.http;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Xiao_Bailong on 2016/2/27.
 */
public class OkHttpContnent {
    private static OkHttpContnent mInstatnce;
    private Handler mHandlerDelively;
    private OkHttpClient mOkHttpClient;

    private OkHttpContnent() {
        mHandlerDelively = new Handler(Looper.getMainLooper());
        mOkHttpClient = new OkHttpClient();
    }

    public static OkHttpContnent getmInstatnce() {
        if (mInstatnce == null)
            synchronized (OkHttpContnent.class) {
                if (mInstatnce == null) {
                    mInstatnce = new OkHttpContnent();
                }
            }
        return mInstatnce;
    }


    /**
     * 异步的get请求
     *
     * @param url
     * @param callback
     */
    private void _getAsyn(String url, final ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        deliveryResult(callback, request);
    }


    /**********************对外暴露的方法**********************************/

    /**
     * 异步请求 --- UI线程
     *
     * @param url
     * @param callback
     */
    public static void getAsyn(String url, ResultCallback callback) {
        getmInstatnce()._getAsyn(url, callback);
    }




    /**
     * 异步请求的简单封装
     *
     * @param callback
     * @param request
     */
    private void deliveryResult(final ResultCallback callback, final Request request) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Response response) {
                try {
                    String responseStr = response.body().string();
                    sendSuccessResultCallback(responseStr, callback);
                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                }
            }
        });
    }

    /**
     * 获取错误回调方法
     *
     * @param request
     * @param e
     * @param callback
     */
    private void sendFailedStringCallback(final Request request, final IOException e, final ResultCallback callback) {
        mHandlerDelively.post(new Runnable() {
            @Override
            public void run() {
                callback.onErroe(request, e);
            }
        });
    }

    /**
     * 获取失败毁掉的方法
     *
     * @param object
     * @param callback
     */
    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        mHandlerDelively.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onResponse(object);
                }
            }
        });
    }


    /**
     * OkHttp请求回调接口
     *
     * @param <T>
     */
    public interface ResultCallback<T> {

        void onErroe(Request request, IOException e);

        void onResponse(T response);
    }

}
