package com.music.ui.entity.shop;

import java.util.List;

/**
 * 订单列表Id
 */
public class OrderListEntity {

    private String create_date;
    private String id;
    private int num;
    private int payment;
    private String product_id;
    private String product_img;
    private String product_title;
    private String status;
    private List<ProductAttrsBean> product_attrs;

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
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

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductAttrsBean> getProduct_attrs() {
        return product_attrs;
    }

    public void setProduct_attrs(List<ProductAttrsBean> product_attrs) {
        this.product_attrs = product_attrs;
    }

    public static class ProductAttrsBean {

        private String id;
        private String name;
        private int type;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
