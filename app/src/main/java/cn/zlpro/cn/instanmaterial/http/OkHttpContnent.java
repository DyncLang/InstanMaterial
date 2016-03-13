package cn.zlpro.cn.instanmaterial.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Xiao_Bailong on 2016/2/27.
 */
public class OkHttpContnent {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpContnent mInstatnce;
    private Handler mHandlerDelively;
    private OkHttpClient mOkHttpClient;
    public Gson gson;

    private OkHttpContnent() {
        mHandlerDelively = new Handler(Looper.getMainLooper());
        mOkHttpClient = new OkHttpClient();
        gson = new Gson();
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


    /**
     * 异步的post请求
     *
     * @param url
     * @param resultCallback
     * @param callback
     * @param paramsMap
     */
    private void _postAsyn(String url, ResultCallback resultCallback, final ResultCallback callback, Map<String, String> paramsMap) {
        Request request = buildPostRequest(url, paramsMap);
        deliveryResult(callback, request);
    }

    /**
     * 构造请求体
     *
     * @param url
     * @param paramsMap
     * @return
     */
    private Request buildPostRequest(String url, Map<String, String> paramsMap) {
        if (paramsMap == null) {
            paramsMap = new HashMap<>();
        }

        JSONObject json = null;
        try {
            json = getJson(paramsMap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(JSON, json.toString());
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

    }


    /**
     * Map 转化为为字符串
     *
     * @param paramsMap
     * @return
     * @throws JSONException
     */
    private JSONObject getJson(Map<String, String> paramsMap) throws JSONException {

        Iterator it = paramsMap.entrySet().iterator();

        JSONObject json = new JSONObject();

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            json.put(entry.getKey().toString(), entry.getValue().toString());
        }

        return json;

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
     * 异步请求
     *
     * @param url
     * @param callback
     * @param paramsMap
     */
    public static void postAsyn(String url, final ResultCallback callback, Map<String, String> paramsMap) {
        getmInstatnce()._postAsyn(url, callback, callback, paramsMap);
    }


    /*************************************************************************/

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
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
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
     * 获取失败回调方法
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
     * 获取成功的回掉方法
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


    /**
     * 请求体的封装
     */
    public static class Param {
        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }


}
