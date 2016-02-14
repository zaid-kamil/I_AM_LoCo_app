package com.xaidworkz.www.i_am_loco_app.helpers;

public class JobsHolder {

    private int imageJob;
    private String imageJobUrl;
    private String textProfileImageUrl;
    private String textProfileName;
    private String textJobTitle;
    private String textJobDescription;
    private String textJobDate;
    private int imageProfile;
    private float textJobBudget;
    private long destinationPoint;
    private String endDate;
    private long startPoint;
    private long optionalIntermediatePoint;


    public JobsHolder(String textJobDate, String textJobDescription, String textJobTitle, String textProfileName, String textProfileImageUrl, String imageJobUrl, float jobBudget, String endDate, long startPoint, long destinationPoint, long optionalIntermediatePoint) {
        this.textJobDate = textJobDate;
        this.textJobDescription = textJobDescription;
        this.textJobTitle = textJobTitle;
        this.textProfileName = textProfileName;
        this.textProfileImageUrl = textProfileImageUrl;
        this.imageJobUrl = imageJobUrl;
        textJobBudget = jobBudget;
        this.destinationPoint = destinationPoint;
        this.endDate = endDate;
        this.startPoint = startPoint;
        this.optionalIntermediatePoint = optionalIntermediatePoint;
    }


    public JobsHolder(int imageJob, String textProfileName, String textJobTitle, String textJobDescription, String textJobDate, int imageProfile) {
        this.imageJob = imageJob;
        this.textProfileName = textProfileName;
        this.textJobTitle = textJobTitle;
        this.textJobDescription = textJobDescription;
        this.textJobDate = textJobDate;
        this.imageProfile = imageProfile;
    }

    public int getImageJob() {
        return imageJob;
    }

    public void setImageJob(int imageJob) {
        this.imageJob = imageJob;
    }

    public String getImageJobUrl() {
        return imageJobUrl;
    }

    public void setImageJobUrl(String imageJobUrl) {
        this.imageJobUrl = imageJobUrl;
    }

    public String getTextProfileImageUrl() {
        return textProfileImageUrl;
    }

    public void setTextProfileImageUrl(String textProfileImageUrl) {
        this.textProfileImageUrl = textProfileImageUrl;
    }

    public String getTextProfileName() {
        return textProfileName;
    }

    public void setTextProfileName(String textProfileName) {
        this.textProfileName = textProfileName;
    }

    public String getTextJobTitle() {
        return textJobTitle;
    }

    public void setTextJobTitle(String textJobTitle) {
        this.textJobTitle = textJobTitle;
    }

    public String getTextJobDescription() {
        return textJobDescription;
    }

    public void setTextJobDescription(String textJobDescription) {
        this.textJobDescription = textJobDescription;
    }

    public String getTextJobDate() {
        return textJobDate;
    }

    public void setTextJobDate(String textJobDate) {
        this.textJobDate = textJobDate;
    }

    public int getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(int imageProfile) {
        this.imageProfile = imageProfile;
    }

    public float getTextJobBudget() {
        return textJobBudget;
    }

    public void setTextJobBudget(float textJobBudget) {
        this.textJobBudget = textJobBudget;
    }

    public long getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(long destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(long startPoint) {
        this.startPoint = startPoint;
    }

    public long getOptionalIntermediatePoint() {
        return optionalIntermediatePoint;
    }

    public void setOptionalIntermediatePoint(long optionalIntermediatePoint) {
        this.optionalIntermediatePoint = optionalIntermediatePoint;
    }
}
