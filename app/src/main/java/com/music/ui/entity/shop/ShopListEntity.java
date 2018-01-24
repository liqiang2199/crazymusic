package com.music.ui.entity.shop;

/**
 * 商城实体类
 */
public class ShopListEntity {

    private String id;
    private String createTime;
    private String modifyTime;
    private String price;
    private String amount;
    private String remain;
    private String totalSalesVolume;
    private String monthSalesVolume;
    private String color;
    private String size;
    private Object specification;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getTotalSalesVolume() {
        return totalSalesVolume;
    }

    public void setTotalSalesVolume(String totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    public String getMonthSalesVolume() {
        return monthSalesVolume;
    }

    public void setMonthSalesVolume(String monthSalesVolume) {
        this.monthSalesVolume = monthSalesVolume;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Object getSpecification() {
        return specification;
    }

    public void setSpecification(Object specification) {
        this.specification = specification;
    }
}
