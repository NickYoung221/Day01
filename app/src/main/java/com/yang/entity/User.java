package com.yang.entity;

/** User类，包含有用户名，用户密码，用户头像
 * Created by yang on 2016/9/26 0026.
 */
public class User {
    private String userName;
    private String userPwd;
    private String userImageUrl;

    public User(String userName, String userPwd, String userImageUrl) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userImageUrl = userImageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }@Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userImageUrl='" + userImageUrl + '\'' +
                '}';
    }



}
