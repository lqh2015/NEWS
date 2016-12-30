package com.lqh.news.model.beans;

import java.util.List;

/**
 * Created by liqinghai on 2016/12/27.
 */
public class TopNews  {
    /**
     * postid : PHOT23RM1000100A
     * hasCover : false
     * hasHead : 1
     * replyCount : 6928
     * hasImg : 1
     * digest :
     * hasIcon : false
     * docid : 9IG74V5H00963VRO_C99THL44bjlishidaiupdateDoc
     * title : 台湾民众抗议日本核食 洪秀柱街头领队
     * order : 1
     * priority : 354
     * lmodify : 2016-12-27 12:48:57
     * boardid : photoview_bbs
     * ads : [{"title":"看客：穿越雪线 极寒中的森林守护者","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/b4f3ce6a24124407afa23cf39a7e620520161227070250.png","subtitle":"","url":"3R710001|2223694"},{"title":"产业工人之城的2016 \u201c这里是东莞\u201d","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/8fe16957737548c9a9280e0964b4e63120161227111140.jpeg","subtitle":"","url":"00AP0001|2223775"},{"title":"90后房产销售 年售一百多套房值5.5亿","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/5efad909d40747cb93d49c7262e65e6320161227082031.jpeg","subtitle":"","url":"00AP0001|2223714"},{"title":"重庆荣昌遇4.8级地震 市民称突然摇晃","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/7af84dca03a944bb99225dc525a36bfe20161227095949.jpeg","subtitle":"","url":"00AP0001|2223763"},{"title":"2016国际年度盘点：国外那些动物网红","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/707cae2885424545841a2430c03cf77c20161227085529.jpeg","subtitle":"","url":"00AO0001|2223716"}]
     * photosetID : 00AP0001|2223809
     * imgsum : 6
     * topic_background : http://img2.cache.netease.com/m/newsapp/reading/cover1/C1348646712614.jpg
     * template : normal1
     * votecount : 5877
     * skipID : 00AP0001|2223809
     * alias : Top News
     * skipType : photoset
     * cid : C1348646712614
     * hasAD : 1
     * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/34a680e74876408784aa0ef87bcb07f320161227123801.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/cf70917d42bc46439a73f8d0e327fb8520161227123801.jpeg"}]
     * source : 网易原创
     * ename : androidnews
     * tname : 头条
     * imgsrc : http://cms-bucket.nosdn.127.net/14b62f21f1b6463cab75911cc06e4e1f20161227123802.jpeg
     * ptime : 2016-12-27 12:38:34
     */

    private List<NewsBean> T1348647909107;//头条新闻


    public List<NewsBean> getT1348647909107() {
        return T1348647909107;
    }

    public void setT1348647909107(List<NewsBean> T1348647909107) {
        this.T1348647909107 = T1348647909107;
    }


    private List<NewsBean> T1348648517839;//娱乐

    public List<NewsBean> getT1348648517839() {
        return T1348648517839;
    }

    public void setT1348648517839(List<NewsBean> T1348648517839) {
        this.T1348648517839 = T1348648517839;
    }

    public static class NewsBean {
        private String postid;
        private boolean hasCover;
        private int hasHead;
        private int replyCount;
        private int hasImg;
        private String digest;
        private boolean hasIcon;
        private String docid;
        private String title;
        private int order;
        private int priority;
        private String lmodify;
        private String boardid;
        private String photosetID;
        private int imgsum;
        private String topic_background;
        private String template;
        private int votecount;
        private String skipID;
        private String alias;
        private String skipType;
        private String cid;
        private int hasAD;
        private String source;
        private String ename;
        private String tname;
        private String imgsrc;
        private String ptime;
        /**
         * title : 看客：穿越雪线 极寒中的森林守护者
         * tag : photoset
         * imgsrc : http://cms-bucket.nosdn.127.net/b4f3ce6a24124407afa23cf39a7e620520161227070250.png
         * subtitle :
         * url : 3R710001|2223694
         */

        private List<AdsBean> ads;
        /**
         * imgsrc : http://cms-bucket.nosdn.127.net/34a680e74876408784aa0ef87bcb07f320161227123801.jpeg
         */

        private List<ImgextraBean> imgextra;

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

        public String getPhotosetID() {
            return photosetID;
        }

        public void setPhotosetID(String photosetID) {
            this.photosetID = photosetID;
        }

        public int getImgsum() {
            return imgsum;
        }

        public void setImgsum(int imgsum) {
            this.imgsum = imgsum;
        }

        public String getTopic_background() {
            return topic_background;
        }

        public void setTopic_background(String topic_background) {
            this.topic_background = topic_background;
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

        public String getSkipID() {
            return skipID;
        }

        public void setSkipID(String skipID) {
            this.skipID = skipID;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getSkipType() {
            return skipType;
        }

        public void setSkipType(String skipType) {
            this.skipType = skipType;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
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

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<AdsBean> getAds() {
            return ads;
        }

        public void setAds(List<AdsBean> ads) {
            this.ads = ads;
        }

        public List<ImgextraBean> getImgextra() {
            return imgextra;
        }

        public void setImgextra(List<ImgextraBean> imgextra) {
            this.imgextra = imgextra;
        }

        public static class AdsBean {
            private String title;
            private String tag;
            private String imgsrc;
            private String subtitle;
            private String url;

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

        public static class ImgextraBean {
            private String imgsrc;

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }
        }
    }
}
