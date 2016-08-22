package com.phone1000.giftgenius.openingservice.info;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 李田 on 2016/8/15.
 */
public class OpenServiceInfo {

    /**
     * addtime : 2016-08-16
     * area : 355服
     * colors : 0
     * gid : 1442977465
     * gname : 皇图
     * iconurl : /allimgs/img_iapp/201510/_1445324917748.png
     * id : 1470900262
     * indexpy : 0
     * isdel : 1
     * istop : 0
     * keyword :
     * linkurl : 08-16 10:00
     * openflag : 3
     * openstate : 1
     * opentype : 2
     * operators : 9377
     * platform : 1
     * score : 5.0
     * starttime : 2016-08-16 10:00
     * state : 1
     * uid : 402881d2554284e2015547751122012a
     * vtypeimage : <i class='android'></i>
     */

    @SerializedName("info")
    private List<InfoBean> serviceinfo;

    public List<InfoBean> getServiceinfo() {
        return serviceinfo;
    }

    public void setServiceinfo(List<InfoBean> serviceinfo) {
        this.serviceinfo = serviceinfo;
    }

    public static class InfoBean {
        private String addtime;
        private String area;
        private int colors;
        private String gid;
        private String gname;
        private String iconurl;
        private int id;
        private String indexpy;
        private int isdel;
        private int istop;
        private String keyword;
        private String linkurl;
        private int openflag;
        private int openstate;
        private int opentype;
        private String operators;
        private String platform;
        private double score;
        private String starttime;
        private int state;
        private String uid;
        private String vtypeimage;

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getColors() {
            return colors;
        }

        public void setColors(int colors) {
            this.colors = colors;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIndexpy() {
            return indexpy;
        }

        public void setIndexpy(String indexpy) {
            this.indexpy = indexpy;
        }

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public int getIstop() {
            return istop;
        }

        public void setIstop(int istop) {
            this.istop = istop;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getLinkurl() {
            return linkurl;
        }

        public void setLinkurl(String linkurl) {
            this.linkurl = linkurl;
        }

        public int getOpenflag() {
            return openflag;
        }

        public void setOpenflag(int openflag) {
            this.openflag = openflag;
        }

        public int getOpenstate() {
            return openstate;
        }

        public void setOpenstate(int openstate) {
            this.openstate = openstate;
        }

        public int getOpentype() {
            return opentype;
        }

        public void setOpentype(int opentype) {
            this.opentype = opentype;
        }

        public String getOperators() {
            return operators;
        }

        public void setOperators(String operators) {
            this.operators = operators;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getVtypeimage() {
            return vtypeimage;
        }

        public void setVtypeimage(String vtypeimage) {
            this.vtypeimage = vtypeimage;
        }
    }
}
