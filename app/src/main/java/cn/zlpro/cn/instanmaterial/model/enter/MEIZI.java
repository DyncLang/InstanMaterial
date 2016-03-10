package cn.zlpro.cn.instanmaterial.model.enter;

import java.util.Date;

/**
 * Created by Xiao_Bailong on 2016/3/6.
 */
public class MEIZI {

    /**
     * _id : 56d8eb3a67765967efcbd69c
     * _ns : ganhuo
     * createdAt : 2016-03-04T09:56:10.964Z
     * desc : 3.4
     * publishedAt : 2016-03-04T12:44:51.926Z
     * source : chrome
     * type : 福利
     * url : http://ww4.sinaimg.cn/large/7a8aed7bjw1f1klhuc8w5j20d30h9gn8.jpg
     * used : true
     * who : 张涵宇
     */


    public String desc;

    public String type;
    public String url;
    public boolean used;
    public String who;
    public Date publishedAt;
    public Date createdAt;

    @Override
    public String toString() {
        return "MEIZI{" +
                "createdAt=" + createdAt +
                ", desc='" + desc + '\'' +
                ", publishedAt=" + publishedAt +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}';
    }
}
