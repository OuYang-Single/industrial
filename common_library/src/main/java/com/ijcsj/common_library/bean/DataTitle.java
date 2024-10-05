package com.ijcsj.common_library.bean;

public class DataTitle {
    private String name;
    private String value;
    private boolean isSelect;

    public DataTitle(){

    }
    public DataTitle(String name,String value,boolean isSelect){
        this.name=name;
        this.isSelect=isSelect;
        this.value=value;
    }
    public DataTitle(String name,boolean isSelect){
        this.name=name;
        this.isSelect=isSelect;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
