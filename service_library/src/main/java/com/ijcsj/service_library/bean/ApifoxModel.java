// ApifoxModel.java

package com.ijcsj.service_library.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;


public class ApifoxModel implements Parcelable, Serializable {
    private List<MenuVO> authorities;
    private List<String> menus;
    private PlayerSdkInfoVO playerSdkInfoVO;
    private List<LoginRoleRspVO> roles;
    private List<MenuVO> simplicityMenuList;
    private StudentSettingLoginRspVO studentSettingVO;
    private String token;
    private LoginUserVO userInfo;
    public ApifoxModel() {

    }
    protected ApifoxModel(Parcel in) {
        authorities = in.createTypedArrayList(MenuVO.CREATOR);
        menus = in.createStringArrayList();
        playerSdkInfoVO = in.readParcelable(PlayerSdkInfoVO.class.getClassLoader());
        roles = in.createTypedArrayList(LoginRoleRspVO.CREATOR);
        simplicityMenuList = in.createTypedArrayList(MenuVO.CREATOR);
        studentSettingVO = in.readParcelable(StudentSettingLoginRspVO.class.getClassLoader());
        token = in.readString();
        userInfo = in.readParcelable(LoginUserVO.class.getClassLoader());
    }

    public static final Creator<ApifoxModel> CREATOR = new Creator<ApifoxModel>() {
        @Override
        public ApifoxModel createFromParcel(Parcel in) {
            return new ApifoxModel(in);
        }

        @Override
        public ApifoxModel[] newArray(int size) {
            return new ApifoxModel[size];
        }
    };

    public List<MenuVO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<MenuVO> authorities) {
        this.authorities = authorities;
    }

    public List<String> getMenus() {
        return menus;
    }

    public void setMenus(List<String> menus) {
        this.menus = menus;
    }

    public PlayerSdkInfoVO getPlayerSdkInfoVO() {
        return playerSdkInfoVO;
    }

    public void setPlayerSdkInfoVO(PlayerSdkInfoVO playerSdkInfoVO) {
        this.playerSdkInfoVO = playerSdkInfoVO;
    }

    public List<LoginRoleRspVO> getRoles() {
        return roles;
    }

    public void setRoles(List<LoginRoleRspVO> roles) {
        this.roles = roles;
    }

    public List<MenuVO> getSimplicityMenuList() {
        return simplicityMenuList;
    }

    public void setSimplicityMenuList(List<MenuVO> simplicityMenuList) {
        this.simplicityMenuList = simplicityMenuList;
    }

    public StudentSettingLoginRspVO getStudentSettingVO() {
        return studentSettingVO;
    }

    public void setStudentSettingVO(StudentSettingLoginRspVO studentSettingVO) {
        this.studentSettingVO = studentSettingVO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginUserVO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(LoginUserVO userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeTypedList(authorities);
        parcel.writeStringList(menus);
        parcel.writeParcelable(playerSdkInfoVO, i);
        parcel.writeTypedList(roles);
        parcel.writeTypedList(simplicityMenuList);
        parcel.writeParcelable(studentSettingVO, i);
        parcel.writeString(token);
        parcel.writeParcelable(userInfo, i);
    }
}


