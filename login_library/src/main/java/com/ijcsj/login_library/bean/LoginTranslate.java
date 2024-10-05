package com.ijcsj.login_library.bean;


public class LoginTranslate {
    /**
     * 手机验证码
     */
    private String captcha;
    /**
     * 验证码签名
     */
    private String captchaKey;
    /**
     * 手机号码
     */
    private String username;
   public LoginTranslate(String username,String captcha,String captchaKey){
       this.username=username;
       this.captcha=captcha;
       this.captchaKey=captchaKey;
   }
    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}