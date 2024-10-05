package com.cn.main_library.base;

public class ProjectBase {
    private String name;
    private String value;
    private String unit;

    public ProjectBase(){

    }
    public ProjectBase(String name,String value,String unit){
        this.name=name;
        this.value=value;
        this.unit=unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
