package cn.zlpro.cn.instanmaterial;

/**
 * Created by Xiao_Bailong on 2016/3/6.
 */
public class ConfigApi {

    /***
     * 全局的Api配置
     * http://gank.io/api/data/Android/10/1
     * http://gank.io/api/data/福利/10/1
     * http://gank.io/api/data/iOS/20/2
     * http://gank.io/api/data/all/20/2
     * <p>
     * <p>
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     */

    public static final int MEIZI_SIZE = 20;
    public static final int GANK_SIZE = 40;

    public static String ApiAdress = "http://gank.io/api/";
    public static String DATA = "data/";
    public static String MEIZI_URL = ApiAdress + DATA + "福利/" + MEIZI_SIZE + "/";


}
