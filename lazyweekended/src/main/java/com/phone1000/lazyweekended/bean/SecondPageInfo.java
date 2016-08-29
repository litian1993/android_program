package com.phone1000.lazyweekended.bean;

import java.util.List;

/**
 * Created by 李田 on 2016/8/28.
 */
public class SecondPageInfo {

    /**
     * status : 200
     * append_info : {}
     * page_total : 0
     * pagenumber : 0
     * result : [{"name":"all","icon_view":"http://image.lanrenzhoumo.com/leo/img/20150820091224_670e5fe509fcc9505682cd1e3aac7ea4.png","cn_name":"全部类目","icon_pressed":"http://image.lanrenzhoumo.com/leo/img/20150820091224_670e5fe509fcc9505682cd1e3aac7ea4.png","children":[],"description":""},{"name":"jzshl","icon_view":"http://image.lanrenzhoumo.com/leo/client/20150820092130_bae2f66e74e39b509bb54ebbaaf90e7f.png","cn_name":"沙龙学堂","icon_pressed":"http://image.lanrenzhoumo.com/leo/client/20150820092130_bae2f66e74e39b509bb54ebbaaf90e7f.png","children":[],"description":""},{"name":"schmsh","icon_view":"http://image.lanrenzhoumo.com/leo/client/diy.png","cn_name":"DIY手作","icon_pressed":"http://image.lanrenzhoumo.com/leo/img/20150820091749_7a92d67b371477f37a27157b46621165.png","children":[],"description":""},{"name":"yhpd","icon_view":"http://image.lanrenzhoumo.com/leo/client/20150213101414_456719_ic_persons_small_gray_small_3x.png","cn_name":"派对聚会","icon_pressed":"http://image.lanrenzhoumo.com/leo/client/20150213101414_251716_ic_persons_small_white_small_3x.png","children":[],"description":""},{"name":"yyych","icon_view":"http://image.lanrenzhoumo.com/leo/client/20150213101412_320270_ic_music_small_gray_small_3x.png","cn_name":"暂不开放","icon_pressed":"http://image.lanrenzhoumo.com/leo/client/20150213101414_770048_ic_music_small_white_small_3x.png","children":[],"description":""},{"name":"sport","icon_view":"http://image.lanrenzhoumo.com/leo/client/20150213101410_237423_ic_basketball_small_gray_small_3x.png","cn_name":"运动健身","icon_pressed":"http://image.lanrenzhoumo.com/leo/client/20150213101410_107420_ic_basketball_small_white_small_3x.png","children":[],"description":""},{"name":"hwyd","icon_view":"http://image.lanrenzhoumo.com/leo/client/20150213101411_472448_ic_c_montain_samll_gray_small_3x.png","cn_name":"户外活动","icon_pressed":"http://image.lanrenzhoumo.com/leo/client/20150213101411_532557_ic_c_montain_samll_white_small_3x.png","children":[],"description":""},{"name":"ch-yj","icon_view":"http://image.lanrenzhoumo.com/leo/client/20150820092032_9daca12325fb6caf1c50e7d0e9d4ea76.png","cn_name":"茶会雅集","icon_pressed":"http://image.lanrenzhoumo.com/leo/client/20150820092032_9daca12325fb6caf1c50e7d0e9d4ea76.png","children":[],"description":""},{"name":"xjhj","icon_view":"http://image.lanrenzhoumo.com/leo/client/20150213101410_214502_ic_c_leaf_small_gray_small_3x.png","cn_name":"文艺生活","icon_pressed":"http://image.lanrenzhoumo.com/leo/client/20150213101410_601938_ic_c_leaf_small_white_small_3x.png","children":[],"description":""}]
     * msg :
     * result_count : 9
     */

    private int status;
    private int page_total;
    private int pagenumber;
    private String msg;
    private int result_count;
    /**
     * name : all
     * icon_view : http://image.lanrenzhoumo.com/leo/img/20150820091224_670e5fe509fcc9505682cd1e3aac7ea4.png
     * cn_name : 全部类目
     * icon_pressed : http://image.lanrenzhoumo.com/leo/img/20150820091224_670e5fe509fcc9505682cd1e3aac7ea4.png
     * children : []
     * description :
     */

    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public int getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(int pagenumber) {
        this.pagenumber = pagenumber;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResult_count() {
        return result_count;
    }

    public void setResult_count(int result_count) {
        this.result_count = result_count;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String name;
        private String icon_view;
        private String cn_name;
        private String icon_pressed;
        private String description;
        private List<?> children;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon_view() {
            return icon_view;
        }

        public void setIcon_view(String icon_view) {
            this.icon_view = icon_view;
        }

        public String getCn_name() {
            return cn_name;
        }

        public void setCn_name(String cn_name) {
            this.cn_name = cn_name;
        }

        public String getIcon_pressed() {
            return icon_pressed;
        }

        public void setIcon_pressed(String icon_pressed) {
            this.icon_pressed = icon_pressed;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }
}
