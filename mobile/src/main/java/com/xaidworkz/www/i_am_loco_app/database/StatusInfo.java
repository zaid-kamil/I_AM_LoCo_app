package com.xaidworkz.www.i_am_loco_app.database;

/**
 * Created by zaid on 14-02-2016.
 */
public class StatusInfo {
    String Name,
            Login,
            ProfilePic,
            Status,
            Photo,
            Date,
            Time;
    int Sno;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getSno() {
        return Sno;
    }

    public void setSno(int sno) {
        Sno = sno;
    }
}
