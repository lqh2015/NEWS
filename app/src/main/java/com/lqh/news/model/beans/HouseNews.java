package com.lqh.news.model.beans;

import java.util.List;

/**
 * Created by liqinghai on 2016/12/27.
 */
public class HouseNews {

    /**
     * postid : C98K4FJH000788HN
     * hasCover : false
     * hasHead : 1
     * replyCount : 338
     * ltitle : 央媒：市场稳得住 百姓有房住
     * hasImg : 1
     * digest : 中央经济工作会议提出，促进房地产市场平稳健康发展。要坚持“房子是用来住的、不是用来炒的”的定位，综合运用金融、土地、财税、投资、立法等手段，加快研究建立符合国情
     * hasIcon : false
     * docid : C98K4FJH000788HN
     * title : 央媒：市场稳得住 百姓有房住
     * order : 1
     * wap_pluginfo : [{"title":"新房","subtitle":"New Building","imgsrc":"http://img6.cache.netease.com/3g/2016/1/26/20160126174956839b0.png","url":"http://m.house.163.com/bj/xf/index.html?refresh=true&reload=true"},{"title":"房价榜","subtitle":"Price Ranking","imgsrc":"http://img2.cache.netease.com/3g/2016/1/26/20160126174951a0193.png","url":"http://m.data.house.163.com/bj/index.html"},{"title":"二手房","subtitle":"Second Housing ","imgsrc":"http://img2.cache.netease.com/3g/2016/1/26/2016012617494723613.png","url":"http://m.esf.house.163.com/bj/"},{"title":"直通车","subtitle":"Group Buying","imgsrc":"http://img6.cache.netease.com/3g/2016/1/26/20160126175001af543.png","url":"http://m.house.163.com/bj/xf/ztc/list/"}]
     * priority : 99
     * lmodify : 2016-12-27 11:48:35
     * boardid : house_bbs
     * ads : [{"docid":"BU1LHT3A00074N6Q","title":"北京购房指南：全北京楼盘全在这了","tag":"doc","imgsrc":"http://cms-bucket.nosdn.127.net/4ab71553dabe4bea8823e0698083ebd220160809145346.jpeg","subtitle":"","url":"BU1LHT3A00074N6Q"}]
     * topic_background : http://img2.cache.netease.com/m/newsapp/reading/cover1/C1392714123307.jpg
     * url_3w : http://house.163.com/16/1227/00/C98K4FJH000788HN.html
     * template : manual
     * votecount : 290
     * alias : beijinghouse
     * cid : C1392714123307
     * url : http://3g.163.com/house/16/1227/00/C98K4FJH000788HN.html
     * hasAD : 1
     * source : 中国经济周刊
     * ename : beijinghouse
     * subtitle :
     * imgsrc : http://cms-bucket.nosdn.127.net/20e74e66f8bc4f118222df95393e187620161227114831.jpeg
     * tname : 北京
     * house_adPortal : [{"title":"新房","subtitle":"New Building","imgsrc":"http://img6.cache.netease.com/3g/2016/1/26/20160126174956839b0.png","url":"http://m.house.163.com/bj/xf/index.html?refresh=true&reload=true"},{"title":"房价榜","subtitle":"Price Ranking","imgsrc":"http://img2.cache.netease.com/3g/2016/1/26/20160126174951a0193.png","url":"http://m.data.house.163.com/bj/index.html"},{"title":"二手房","subtitle":"Second Housing ","imgsrc":"http://img2.cache.netease.com/3g/2016/1/26/2016012617494723613.png","url":"http://m.esf.house.163.com/bj/"},{"title":"直通车","subtitle":"Group Buying","imgsrc":"http://img6.cache.netease.com/3g/2016/1/26/20160126175001af543.png","url":"http://m.house.163.com/bj/xf/ztc/list/"}]
     * ptime : 2016-12-27 00:32:58
     */

    private List<HouseBean> houseBeanList;

    public List<HouseBean> getHouseBeanList() {
        return houseBeanList;
    }

    public void setHouseBeanList(List<HouseBean> houseBeanList) {
        this.houseBeanList = houseBeanList;
    }

    public static class HouseBean {
        private String postid;
        private boolean hasCover;
        private int hasHead;
        private int replyCount;
        private String ltitle;
        private int hasImg;
        private String digest;
        private boolean hasIcon;
        private String docid;
        private String title;
        private int order;
        private int priority;
        private String lmodify;
        private String boardid;
        private String topic_background;
        private String url_3w;
        private String template;
        private int votecount;
        private String alias;
        private String cid;
        private String url;
        private int hasAD;
        private String source;
        private String ename;
        private String subtitle;
        private String imgsrc;
        private String tname;
        private String ptime;
        /**
         * title : 新房
         * subtitle : New Building
         * imgsrc : http://img6.cache.netease.com/3g/2016/1/26/20160126174956839b0.png
         * url : http://m.house.163.com/bj/xf/index.html?refresh=true&reload=true
         */

        private List<WapPluginfoBean> wap_pluginfo;
        /**
         * docid : BU1LHT3A00074N6Q
         * title : 北京购房指南：全北京楼盘全在这了
         * tag : doc
         * imgsrc : http://cms-bucket.nosdn.127.net/4ab71553dabe4bea8823e0698083ebd220160809145346.jpeg
         * subtitle :
         * url : BU1LHT3A00074N6Q
         */

        private List<AdsBean> ads;
        /**
         * title : 新房
         * subtitle : New Building
         * imgsrc : http://img6.cache.netease.com/3g/2016/1/26/20160126174956839b0.png
         * url : http://m.house.163.com/bj/xf/index.html?refresh=true&reload=true
         */

        private List<HouseAdPortalBean> house_adPortal;

        public String getPostid() {
            return postid;
        }

        public void setPostid(String postid) {
            this.postid = postid;
        }

        public boolean isHasCover() {
            return hasCover;
        }

        public void setHasCover(boolean hasCover) {
            this.hasCover = hasCover;
        }

        public int getHasHead() {
            return hasHead;
        }

        public void setHasHead(int hasHead) {
            this.hasHead = hasHead;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getLtitle() {
            return ltitle;
        }

        public void setLtitle(String ltitle) {
            this.ltitle = ltitle;
        }

        public int getHasImg() {
            return hasImg;
        }

        public void setHasImg(int hasImg) {
            this.hasImg = hasImg;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public boolean isHasIcon() {
            return hasIcon;
        }

        public void setHasIcon(boolean hasIcon) {
            this.hasIcon = hasIcon;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getLmodify() {
            return lmodify;
        }

        public void setLmodify(String lmodify) {
            this.lmodify = lmodify;
        }

        public String getBoardid() {
            return boardid;
        }

        public void setBoardid(String boardid) {
            this.boardid = boardid;
        }

        public String getTopic_background() {
            return topic_background;
        }

        public void setTopic_background(String topic_background) {
            this.topic_background = topic_background;
        }

        public String getUrl_3w() {
            return url_3w;
        }

        public void setUrl_3w(String url_3w) {
            this.url_3w = url_3w;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public int getVotecount() {
            return votecount;
        }

        public void setVotecount(int votecount) {
            this.votecount = votecount;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getHasAD() {
            return hasAD;
        }

        public void setHasAD(int hasAD) {
            this.hasAD = hasAD;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<WapPluginfoBean> getWap_pluginfo() {
            return wap_pluginfo;
        }

        public void setWap_pluginfo(List<WapPluginfoBean> wap_pluginfo) {
            this.wap_pluginfo = wap_pluginfo;
        }

        public List<AdsBean> getAds() {
            return ads;
        }

        public void setAds(List<AdsBean> ads) {
            this.ads = ads;
        }

        public List<HouseAdPortalBean> getHouse_adPortal() {
            return house_adPortal;
        }

        public void setHouse_adPortal(List<HouseAdPortalBean> house_adPortal) {
            this.house_adPortal = house_adPortal;
        }

        public static class WapPluginfoBean {
            private String title;
            private String subtitle;
            private String imgsrc;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class AdsBean {
            private String docid;
            private String title;
            private String tag;
            private String imgsrc;
            private String subtitle;
            private String url;

            public String getDocid() {
                return docid;
            }

            public void setDocid(String docid) {
                this.docid = docid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class HouseAdPortalBean {
            private String title;
            private String subtitle;
            private String imgsrc;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
