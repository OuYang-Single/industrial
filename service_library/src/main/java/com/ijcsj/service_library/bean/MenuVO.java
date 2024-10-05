package com.ijcsj.service_library.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class MenuVO implements Parcelable, Serializable {
    /**
     * 颜色
     */
    private String color;
    /**
     * 组件
     */
    private String component;
    /**
     * 删除状态 0正常 1已删除
     */
    private Boolean delFlag;
    /**
     * 图标
     */
    private String icon;
    /**
     * id
     */
    private Long id;
    /**
     * 业务id
     */
    private String idCode;
    /**
     * 是否展示(0:隐藏,1:展示)
     */
    private Long isShow;
    /**
     * 菜单/按钮名称
     */
    private String menuName;
    /**
     * 菜单URL
     */
    private String menuPath;
    /**
     * 菜单展示类型
     */
    private MenuShowType menuShowType;
    /**
     * 菜单类型(0:一级菜单,1:子菜单,3:按钮权限
     */
    private String menuType;
    /**
     * 排序
     */
    private Long orderNum;
    /**
     * 上级菜单ID
     */
    private Long parentId;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 外链打开方式(组件,外链,内链)
     */
    private String target;
    public MenuVO () {

    }

    protected MenuVO(Parcel in) {
        color = in.readString();
        component = in.readString();
        byte tmpDelFlag = in.readByte();
        delFlag = tmpDelFlag == 0 ? null : tmpDelFlag == 1;
        icon = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        idCode = in.readString();
        if (in.readByte() == 0) {
            isShow = null;
        } else {
            isShow = in.readLong();
        }
        menuName = in.readString();
        menuPath = in.readString();
        menuType = in.readString();
        if (in.readByte() == 0) {
            orderNum = null;
        } else {
            orderNum = in.readLong();
        }
        if (in.readByte() == 0) {
            parentId = null;
        } else {
            parentId = in.readLong();
        }
        perms = in.readString();
        remark = in.readString();
        target = in.readString();
    }

    public static final Creator<MenuVO> CREATOR = new Creator<MenuVO>() {
        @Override
        public MenuVO createFromParcel(Parcel in) {
            return new MenuVO(in);
        }

        @Override
        public MenuVO[] newArray(int size) {
            return new MenuVO[size];
        }
    };

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public Long getIsShow() {
        return isShow;
    }

    public void setIsShow(Long isShow) {
        this.isShow = isShow;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public MenuShowType getMenuShowType() {
        return menuShowType;
    }

    public void setMenuShowType(MenuShowType menuShowType) {
        this.menuShowType = menuShowType;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(color);
        parcel.writeString(component);
        parcel.writeByte((byte) (delFlag == null ? 0 : delFlag ? 1 : 2));
        parcel.writeString(icon);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(idCode);
        if (isShow == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(isShow);
        }
        parcel.writeString(menuName);
        parcel.writeString(menuPath);
        parcel.writeString(menuType);
        if (orderNum == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(orderNum);
        }
        if (parentId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(parentId);
        }
        parcel.writeString(perms);
        parcel.writeString(remark);
        parcel.writeString(target);
    }
}
