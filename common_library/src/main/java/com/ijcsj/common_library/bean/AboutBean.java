package com.ijcsj.common_library.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ijcsj.common_library.BR;

import java.io.Serializable;

public class AboutBean extends BaseObservable implements Serializable {
    private  String tips2_txt;
    private  String branch_icon;
    private  String tips3_value;
    private  String tips2_value;
    private  String text1;
    private  String text2;
    private  String tips1_value;
    private  String software_version;
    private  String software_name;
    private  String tips3_txt;
    private  String copyright_en;
    private  String btn_text;
    private  String copyright_cn;
    private  String tips1_txt;
    private  String btn_value;

    public AboutBean(){

    }
    public String getTips2_txt() {
        return tips2_txt;
    }

    public void setTips2_txt(String tips2_txt) {
        this.tips2_txt = tips2_txt;
    }
    @Bindable
    public String getBranch_icon() {
        return branch_icon;
    }

    public void setBranch_icon(String branch_icon) {
        this.branch_icon = branch_icon;
        notifyPropertyChanged( BR.branch_icon);
    }

    public String getTips3_value() {
        return tips3_value;
    }

    public void setTips3_value(String tips3_value) {
        this.tips3_value = tips3_value;
    }

    public String getTips2_value() {
        return tips2_value;
    }

    public void setTips2_value(String tips2_value) {
        this.tips2_value = tips2_value;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }
    @Bindable
    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
        notifyPropertyChanged( BR.text2);
    }

    public String getTips1_value() {
        return tips1_value;
    }

    public void setTips1_value(String tips1_value) {
        this.tips1_value = tips1_value;
    }

    public String getSoftware_version() {
        return software_version;
    }

    public void setSoftware_version(String software_version) {
        this.software_version = software_version;
    }
    @Bindable
    public String getSoftware_name() {
        return software_name;
    }

    public void setSoftware_name(String software_name) {
        this.software_name = software_name;
        notifyPropertyChanged( BR.software_name);
    }

    public String getTips3_txt() {
        return tips3_txt;
    }

    public void setTips3_txt(String tips3_txt) {
        this.tips3_txt = tips3_txt;
    }
    @Bindable
    public String getCopyright_en() {
        return copyright_en;
    }

    public void setCopyright_en(String copyright_en) {
        this.copyright_en = copyright_en;
        notifyPropertyChanged( BR.copyright_en);
    }

    public String getBtn_text() {
        return btn_text;
    }

    public void setBtn_text(String btn_text) {
        this.btn_text = btn_text;
    }

    public String getCopyright_cn() {
        return copyright_cn;
    }

    public void setCopyright_cn(String copyright_cn) {
        this.copyright_cn = copyright_cn;
    }

    public String getTips1_txt() {
        return tips1_txt;
    }

    public void setTips1_txt(String tips1_txt) {
        this.tips1_txt = tips1_txt;
    }

    public String getBtn_value() {
        return btn_value;
    }

    public void setBtn_value(String btn_value) {
        this.btn_value = btn_value;
    }
}
