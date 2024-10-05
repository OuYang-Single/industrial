package com.ijcsj.mylibrary.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ijcsj.my_library.BR;

import java.util.List;

public class Retroaction  extends BaseObservable {

    /**
     * 意见内容
     */
    private String adviseContent;
    /**
     * 意见标题
     */
    private String adviseTitle;
    /**
     * 联系方式(qq,微信,邮箱,电话均可,不做校验)
     */
    private String contactWay;
    /**
     * 删除的文件key
     */
    private List<String> delFileKeys;
    /**
     * 本次上传成功的文件key
     */
    private List<String> sucessFileKey;
    public Retroaction(){}

    @Bindable
    public String getAdviseContent() {
        return adviseContent;
    }

    public void setAdviseContent(String adviseContent) {
        this.adviseContent = adviseContent;
        notifyPropertyChanged( BR.adviseContent);
    }

    public String getAdviseTitle() {
        return adviseTitle;
    }

    public void setAdviseTitle(String adviseTitle) {
        this.adviseTitle = adviseTitle;
    }
    @Bindable
    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
        notifyPropertyChanged( BR.adviseContent);
    }

    public List<String> getDelFileKeys() {
        return delFileKeys;
    }

    public void setDelFileKeys(List<String> delFileKeys) {
        this.delFileKeys = delFileKeys;
    }

    public List<String> getSucessFileKey() {
        return sucessFileKey;
    }

    public void setSucessFileKey(List<String> sucessFileKey) {
        this.sucessFileKey = sucessFileKey;
    }
}
