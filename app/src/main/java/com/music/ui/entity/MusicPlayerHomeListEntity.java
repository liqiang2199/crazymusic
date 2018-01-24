package com.music.ui.entity;

import java.util.List;

/**
 */
public class MusicPlayerHomeListEntity {
    private String title;
    private List<CreateMusicEntity> musicEntities;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CreateMusicEntity> getMusicEntities() {
        return musicEntities;
    }

    public void setMusicEntities(List<CreateMusicEntity> musicEntities) {
        this.musicEntities = musicEntities;
    }
}
