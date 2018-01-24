package com.music.ui.entity.shop;

import java.util.List;

/**
 * 商品详情
 */
public class GoodsInfoEntity {
    private float cost_price;
    private String html;
    private String id;
    private String name;
    private float now_price;
    private String simple_desc;
    private String simple_name;
    private String tag_name;
    private String type_name;
    private List<AttrListBean> attrList;
    private List<ImgsBean> imgs;

    public float getCost_price() {
        return cost_price;
    }

    public void setCost_price(float cost_price) {
        this.cost_price = cost_price;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

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

    public float getNow_price() {
        return now_price;
    }

    public void setNow_price(float now_price) {
        this.now_price = now_price;
    }

    public String getSimple_desc() {
        return simple_desc;
    }

    public void setSimple_desc(String simple_desc) {
        this.simple_desc = simple_desc;
    }

    public String getSimple_name() {
        return simple_name;
    }

    public void setSimple_name(String simple_name) {
        this.simple_name = simple_name;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public List<AttrListBean> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<AttrListBean> attrList) {
        this.attrList = attrList;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public static class AttrListBean {
        private float increase_price;
        private String name;
        private int number;
        private String product_attr_id;
        private String type;
        private String value;

        public float getIncrease_price() {
            return increase_price;
        }

        public void setIncrease_price(float increase_price) {
            this.increase_price = increase_price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getProduct_attr_id() {
            return product_attr_id;
        }

        public void setProduct_attr_id(String product_attr_id) {
            this.product_attr_id = product_attr_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }


    }

    public static class ImgsBean {
        private String product_img;

        public String getProduct_img() {
            return product_img;
        }

        public void setProduct_img(String product_img) {
            this.product_img = product_img;
        }
    }
}
