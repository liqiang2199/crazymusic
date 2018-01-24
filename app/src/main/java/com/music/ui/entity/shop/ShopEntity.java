package com.music.ui.entity.shop;

import java.util.List;

/**
 * Created by feq on 2017/12/31.
 */

public class ShopEntity {

    /**
     * code : 0
     * data : [{"attrs":["橘黄","27"],"id":"4D64B182BC251B2D192E","number":5,"payment":1111,"product_attr_ids":"1,4","product_id":"F61EC4B360E94CEEA300231AF01C3B42","product_img":"123213","product_name":"吉他","state":1},{"attrs":["橘黄","26"],"id":"4CDC99D38DD771878B46","number":5,"payment":1111,"product_attr_ids":"1,3","product_id":"F61EC4B360E94CEEA300231AF01C3B42","product_img":"123213","product_name":"吉2他","state":1}]
     * msg : 111
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
         * attrs : ["橘黄","27"]
         * id : 4D64B182BC251B2D192E
         * number : 5
         * payment : 1111
         * product_attr_ids : 1,4
         * product_id : F61EC4B360E94CEEA300231AF01C3B42
         * product_img : 123213
         * product_name : 吉他
         * state : 1
         */

        private String id;
        private int number;
        private int payment;
        private String product_attr_ids;
        private String product_id;
        private String product_img;
        private String product_name;
        private int state;
        private List<String> attrs;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }

        public String getProduct_attr_ids() {
            return product_attr_ids;
        }

        public void setProduct_attr_ids(String product_attr_ids) {
            this.product_attr_ids = product_attr_ids;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_img() {
            return product_img;
        }

        public void setProduct_img(String product_img) {
            this.product_img = product_img;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public List<String> getAttrs() {
            return attrs;
        }

        public void setAttrs(List<String> attrs) {
            this.attrs = attrs;
        }
    }
}
