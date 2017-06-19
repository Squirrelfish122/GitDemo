package com.zhh.hyman.gitdemo;

/**
 *
 */

public class PhoneNumberBean {

    public static final int TYPE_NUMBER = 0;
    public static final int TYPE_COMMAND = 1;
    public static final int TYPE_PICTURE_COMMAND = 2;

    public static final int ACTION_COMMAND_SEND = 100;

    public static final int ACTION_PICTURE_COMMAND_DELETE = 1000;

    private String text;
    private Integer imageResourceId;
    private int type;
    private int action;

    public PhoneNumberBean(int type,String text, Integer imageResourceId,int action) {
        this.type = type;
        this.text = text;
        this.imageResourceId = imageResourceId;
        this.action = action;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public Integer getImageResourceId() {
        return imageResourceId;
    }

    public int getAction() {
        return action;
    }
}
