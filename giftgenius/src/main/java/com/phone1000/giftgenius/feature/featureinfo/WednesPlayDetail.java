package com.phone1000.giftgenius.feature.featureinfo;

import java.util.List;

/**
 * Created by 李田 on 2016/8/17.
 */
public class WednesPlayDetail {

    /**
     * appdesc :
     * appicon : /allimgs/img_iapp/201603/_1458787584145.jpg
     * appid : 1458787671
     * appname : 被错过的天堂
     * appvtype : 1,2
     * cid : 112
     * id : 1203
     * typename : 冒险解密
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String appdesc;
        private String appicon;
        private String appid;
        private String appname;
        private String appvtype;
        private int cid;
        private int id;
        private String typename;

        public String getAppdesc() {
            return appdesc;
        }

        public void setAppdesc(String appdesc) {
            this.appdesc = appdesc;
        }

        public String getAppicon() {
            return appicon;
        }

        public void setAppicon(String appicon) {
            this.appicon = appicon;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getAppvtype() {
            return appvtype;
        }

        public void setAppvtype(String appvtype) {
            this.appvtype = appvtype;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }
}
