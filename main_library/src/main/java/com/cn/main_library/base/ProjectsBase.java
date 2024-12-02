package com.cn.main_library.base;

public class ProjectsBase {
    private String name;
    private int value;

    public ProjectsBase(){

    }
    public ProjectsBase(String name, int value){
        this.name=name;
        this.value=value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
