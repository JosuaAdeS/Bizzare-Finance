package com.example.bizzarefinance;

public class UserDetail {
    private String fullname,NIK,handphone,username;

    public static String uid ;
    public UserDetail(){

    }

    public UserDetail(String fullname, String NIK, String handphone, String username ) {
        this.fullname = fullname;
        this.NIK = NIK;
        this.handphone = handphone;
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
