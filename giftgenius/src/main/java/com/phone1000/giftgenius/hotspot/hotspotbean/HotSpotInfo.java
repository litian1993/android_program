package com.phone1000.giftgenius.hotspot.hotspotbean;

import java.util.List;

/**
 * Created by 李田 on 2016/8/18.
 */
public class HotSpotInfo {

    /**
     * flag : true
     * info : {"push1":[{"addtime":"2016-08-17 11:19:47.0","appid":"1441623161","clicks":12112,"flag":1,"id":8,"logo":"/allimgs/img_iapp/201608/_1470623898433.jpg","name":"航海归来","platform":0,"type":1,"typename":"策略塔防"},{"addtime":"2016-08-04 21:33:32.0","appid":"1443491252","clicks":14283,"flag":1,"id":10,"logo":"/allimgs/img_iapp/201509/_1443491274999.png","name":"王者荣耀","platform":0,"size":"360M","type":1,"typename":"动作格斗"},{"addtime":"2016-07-23 14:04:44.0","appid":"1428908867","clicks":13262,"flag":0,"id":37,"logo":"/allimgs/img_iapp/201605/_1463474275867.png","name":"火影忍者","platform":0,"size":"342M","type":1,"typename":"动作格斗"}],"push2":[{"addtime":"2016-04-03 17:40:23.0","appid":"1451971043","clicks":2598,"flag":1,"id":28,"logo":"/allimgs/img_iapp/201601/_1451970639500.jpg","name":"部落冲突:皇室战争","platform":0,"size":"98.1M","type":0,"typename":"卡牌游戏"},{"addtime":"2016-04-03 17:39:10.0","appid":"1451550790","clicks":100,"flag":1,"id":27,"logo":"/allimgs/img_iapp/201601/_1454054325445.png","name":"天天酷跑3D","platform":0,"size":"244M","type":0,"typename":"休闲益智"},{"addtime":"2016-03-24 13:41:41.0","appid":"1438084072","clicks":4522,"flag":1,"id":11,"logo":"/allimgs/img_iapp/201507/_1438084023090.jpg","name":"全民超神","platform":0,"size":"401 MB","type":0,"typename":"角色扮演"},{"addtime":"2016-03-23 20:02:16.0","appid":"1421918699","clicks":100,"flag":1,"id":23,"logo":"/allimgs/img_iapp/201601/_1452236952103.png","name":"熹妃传","platform":0,"size":"158 MB","type":0,"typename":"角色扮演"},{"addtime":"2016-01-09 18:12:19.0","appid":"1420564361","clicks":3533,"flag":1,"id":9,"logo":"/userfiles/applogo/_1420610936640.jpg","name":"全民突击","platform":0,"size":"223M","type":0,"typename":"射击空战"},{"addtime":"2016-01-09 18:08:27.0","appid":"1421467036","clicks":2532,"flag":1,"id":12,"logo":"/allimgs/img_iapp/201603/_1459327042485.png","name":"热血传奇手机版","platform":0,"size":" 516 MB","type":0,"typename":"角色扮演"}]}
     */

    private boolean flag;
    private InfoBean info;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * addtime : 2016-08-17 11:19:47.0
         * appid : 1441623161
         * clicks : 12112
         * flag : 1
         * id : 8
         * logo : /allimgs/img_iapp/201608/_1470623898433.jpg
         * name : 航海归来
         * platform : 0
         * type : 1
         * typename : 策略塔防
         */

        private List<Push1Bean> push1;
        /**
         * addtime : 2016-04-03 17:40:23.0
         * appid : 1451971043
         * clicks : 2598
         * flag : 1
         * id : 28
         * logo : /allimgs/img_iapp/201601/_1451970639500.jpg
         * name : 部落冲突:皇室战争
         * platform : 0
         * size : 98.1M
         * type : 0
         * typename : 卡牌游戏
         */

        private List<Push2Bean> push2;

        public List<Push1Bean> getPush1() {
            return push1;
        }

        public void setPush1(List<Push1Bean> push1) {
            this.push1 = push1;
        }

        public List<Push2Bean> getPush2() {
            return push2;
        }

        public void setPush2(List<Push2Bean> push2) {
            this.push2 = push2;
        }

        public static class Push1Bean {
            private String addtime;
            private String appid;
            private int clicks;
            private int flag;
            private int id;
            private String size;

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            private String logo;
            private String name;
            private int platform;
            private int type;
            private String typename;

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public int getClicks() {
                return clicks;
            }

            public void setClicks(int clicks) {
                this.clicks = clicks;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPlatform() {
                return platform;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }
        }

        public static class Push2Bean {
            private String addtime;
            private String appid;
            private int clicks;
            private int flag;
            private int id;
            private String logo;
            private String name;
            private int platform;
            private String size;
            private int type;
            private String typename;

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public int getClicks() {
                return clicks;
            }

            public void setClicks(int clicks) {
                this.clicks = clicks;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPlatform() {
                return platform;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }
        }
    }
}
