package com.music.ui.entity.shop;

import java.util.List;

/**
 * Created by feq on 2017/12/24.
 */

public class GoodsAllEntity {

    /**
     * code : 0
     * data : [{"childList":[{"id":"AA54DAF6FBE1466FA7C1A667089AAD17","parent_id":"4","type_name":"测试类型2","type_pic":"http://crazymusic-bucket.oss-cn-shenzhen.aliyuncs.com/5c730183点击直播.png"},{"id":"DC9FBD31779B4348A396F1DBE0837103","parent_id":"4","type_name":"请问3","type_pic":"http://crazymusic-bucket.oss-cn-shenzhen.aliyuncs.com/ae462f49线下约.jpg"}],"id":"4","parent_id":"0","type_name":"尤克里里"},{"childList":[{"id":"AFBB673FF55842CDB5BA32631F551097","parent_id":"5","type_name":"测试类型2","type_pic":"http://crazymusic-bucket.oss-cn-shenzhen.aliyuncs.com/80098b812.png"}],"id":"5","parent_id":"0","type_name":"吉他配件"},{"childList":[{"id":"1","parent_id":"6","type_name":"民谣吉他"},{"id":"2","parent_id":"6","type_name":"古典吉他"},{"id":"3","parent_id":"6","type_name":"电吉他"}],"id":"6","parent_id":"0","type_name":"吉他"},{"childList":[],"id":"2C1E39DAD47341C094B8F8BAF807F52A","parent_id":"0","type_name":"请问1"}]
     * msg : 成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * childList : [{"id":"AA54DAF6FBE1466FA7C1A667089AAD17","parent_id":"4","type_name":"测试类型2","type_pic":"http://crazymusic-bucket.oss-cn-shenzhen.aliyuncs.com/5c730183点击直播.png"},{"id":"DC9FBD31779B4348A396F1DBE0837103","parent_id":"4","type_name":"请问3","type_pic":"http://crazymusic-bucket.oss-cn-shenzhen.aliyuncs.com/ae462f49线下约.jpg"}]
         * id : 4
         * parent_id : 0
         * type_name : 尤克里里
         */

        private String id;
        private String parent_id;
        private String type_name;
        private List<ChildListBean> childList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public List<ChildListBean> getChildList() {
            return childList;
        }

        public void setChildList(List<ChildListBean> childList) {
            this.childList = childList;
        }

        public static class ChildListBean {
            /**
             * id : AA54DAF6FBE1466FA7C1A667089AAD17
             * parent_id : 4
             * type_name : 测试类型2
             * type_pic : http://crazymusic-bucket.oss-cn-shenzhen.aliyuncs.com/5c730183点击直播.png
             */

            private String id;
            private String parent_id;
            private String type_name;
            private String type_pic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getType_pic() {
                return type_pic;
            }

            public void setType_pic(String type_pic) {
                this.type_pic = type_pic;
            }
        }
    }
}
