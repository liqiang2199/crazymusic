package com.music.ui.entity.shop;

import java.util.List;

/**
 * 商城首页实体类
 */
public class ShopIndexEntity {

    private List<ProductDatasBean> product_datas;
    private List<TypeDatasBean> type_datas;

    public List<ProductDatasBean> getProduct_datas() {
        return product_datas;
    }

    public void setProduct_datas(List<ProductDatasBean> product_datas) {
        this.product_datas = product_datas;
    }

    public List<TypeDatasBean> getType_datas() {
        return type_datas;
    }

    public void setType_datas(List<TypeDatasBean> type_datas) {
        this.type_datas = type_datas;
    }

    public static class ProductDatasBean {
        private String id;
        private String name;
        private String now_price;
        private String simple_desc;
        private float recommend_star;
        private String recommend_desc;
        private String product_img;
        private String sort;
        private String lowest_price;
        private String higst_price;

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

        public String getNow_price() {
            return now_price;
        }

        public void setNow_price(String now_price) {
            this.now_price = now_price;
        }

        public String getSimple_desc() {
            return simple_desc;
        }

        public void setSimple_desc(String simple_desc) {
            this.simple_desc = simple_desc;
        }

        public float getRecommend_star() {
            return recommend_star;
        }

        public void setRecommend_star(float recommend_star) {
            this.recommend_star = recommend_star;
        }

        public String getRecommend_desc() {
            return recommend_desc;
        }

        public void setRecommend_desc(String recommend_desc) {
            this.recommend_desc = recommend_desc;
        }

        public String getProduct_img() {
            return product_img;
        }

        public void setProduct_img(String product_img) {
            this.product_img = product_img;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getLowest_price() {
            return lowest_price;
        }

        public void setLowest_price(String lowest_price) {
            this.lowest_price = lowest_price;
        }

        public String getHigst_price() {
            return higst_price;
        }

        public void setHigst_price(String higst_price) {
            this.higst_price = higst_price;
        }
    }

    public static class TypeDatasBean {
        private String id;
        private String type_name;
        private String parent_id;
        private long create_date;
        private String del_flag;
        private String sort;
        private String type_pic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public long getCreate_date() {
            return create_date;
        }

        public void setCreate_date(long create_date) {
            this.create_date = create_date;
        }

        public String getDel_flag() {
            return del_flag;
        }

        public void setDel_flag(String del_flag) {
            this.del_flag = del_flag;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getType_pic() {
            return type_pic;
        }

        public void setType_pic(String type_pic) {
            this.type_pic = type_pic;
        }
    }
}
