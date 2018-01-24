package com.music.ui.entity;

import java.util.List;

/**
 * 社区列表实体类
 */
public class CommunityListEntity {

    private String content;
    private String create_date;
    private int evaluate_count;
    private String head_img;
    private String id;
    private boolean is_perfect;
    private String nick_name;
    private int perfect_count;
    private List<String> imgList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getEvaluate_count() {
        return evaluate_count;
    }

    public void setEvaluate_count(int evaluate_count) {
        this.evaluate_count = evaluate_count;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIs_perfect() {
        return is_perfect;
    }

    public void setIs_perfect(boolean is_perfect) {
        this.is_perfect = is_perfect;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getPerfect_count() {
        return perfect_count;
    }

    public void setPerfect_count(int perfect_count) {
        this.perfect_count = perfect_count;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
