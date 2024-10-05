package com.ijcsj.common_library.bean;

public class VideoSetting {
    private String name;
    private boolean isChoice;
    private int type;
    private int content;
    private float contents;
    public VideoSetting(){

    }
    public VideoSetting(String name,int type,int content,float contents){
        this.name=name;
        this.content=content;
        this.type=type;
        this.contents=contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public float getContents() {
        return contents;
    }

    public void setContents(float contents) {
        this.contents = contents;
    }
}
