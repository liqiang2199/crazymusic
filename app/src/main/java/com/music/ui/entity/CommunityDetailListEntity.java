package com.music.ui.entity;

import java.util.List;

/**
 * 社区详情实体类--
 */
public class CommunityDetailListEntity {

    private String content;
    private String head_img;
    private String id;
    private String nick_name;
    private String user_id;
//    private List<?> childList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

//    public List<?> getChildList() {
//        return childList;
//    }
//
//    public void setChildList(List<?> childList) {
//        this.childList = childList;
//    }
}
