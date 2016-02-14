package com.xaidworkz.www.i_am_loco_app.database;

/**
 * Created by zaid on 14-02-2016.
 */
public class JobInfo {
    int JobId;
    String Login,
            Name,
            ProfilePic,
            JobTitle,
            JobDescription1,
            Latitude1,
            Longitude1,
            JobDescription2,
            Latitude2,
            Longitude2,
            DestLatitude,
            Budget,
            JobDate,
            JobTime,
            JobDeadlineDate,
            JobDeadlineTime,
            JobStatus,
            JobDoneByLogin;

    public int getJobId() {
        return JobId;
    }

    public void setJobId(int jobId) {
        JobId = jobId;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getJobDescription1() {
        return JobDescription1;
    }

    public void setJobDescription1(String jobDescription1) {
        JobDescription1 = jobDescription1;
    }

    public String getLatitude1() {
        return Latitude1;
    }

    public void setLatitude1(String latitude1) {
        Latitude1 = latitude1;
    }

    public String getLongitude1() {
        return Longitude1;
    }

    public void setLongitude1(String longitude1) {
        Longitude1 = longitude1;
    }

    public String getJobDescription2() {
        return JobDescription2;
    }

    public void setJobDescription2(String jobDescription2) {
        JobDescription2 = jobDescription2;
    }

    public String getLatitude2() {
        return Latitude2;
    }

    public void setLatitude2(String latitude2) {
        Latitude2 = latitude2;
    }

    public String getLongitude2() {
        return Longitude2;
    }

    public void setLongitude2(String longitude2) {
        Longitude2 = longitude2;
    }

    public String getDestLatitude() {
        return DestLatitude;
    }

    public void setDestLatitude(String destLatitude) {
        DestLatitude = destLatitude;
    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String budget) {
        Budget = budget;
    }

    public String getJobDate() {
        return JobDate;
    }

    public void setJobDate(String jobDate) {
        JobDate = jobDate;
    }

    public String getJobTime() {
        return JobTime;
    }

    public void setJobTime(String jobTime) {
        JobTime = jobTime;
    }

    public String getJobDeadlineDate() {
        return JobDeadlineDate;
    }

    public void setJobDeadlineDate(String jobDeadlineDate) {
        JobDeadlineDate = jobDeadlineDate;
    }

    public String getJobDeadlineTime() {
        return JobDeadlineTime;
    }

    public void setJobDeadlineTime(String jobDeadlineTime) {
        JobDeadlineTime = jobDeadlineTime;
    }

    public String getJobStatus() {
        return JobStatus;
    }

    public void setJobStatus(String jobStatus) {
        JobStatus = jobStatus;
    }

    public String getJobDoneByLogin() {
        return JobDoneByLogin;
    }

    public void setJobDoneByLogin(String jobDoneByLogin) {
        JobDoneByLogin = jobDoneByLogin;
    }
}
