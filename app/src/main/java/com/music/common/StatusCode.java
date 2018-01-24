package com.music.common;

public enum StatusCode {
    SUCCESS(0),

    FAILURE(-1);

    int type;

    StatusCode(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
