package com.chandrapal.stories;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private String storiesId;
    private String storiesTitle;
    private String storiesTags;
    private String storiesLanguage;
    private String storiesCoverImage;
    private String storiesStoriesText;
    private String storiesUploadedBy;
    private String storiesUserId;
    private String storiesType;
    private String storiesUploadedTime;
    private String storiesStatus;
    private String storiesPinned;

    public Model(){}

    public Model(String storiesId, String storiesTitle, String storiesTags, String storiesLanguage, String storiesCoverImage, String storiesStoriesText, String storiesUploadedBy, String storiesUserId, String storiesType, String storiesUploadedTime, String storiesStatus, String storiesPinned) {
        this.storiesId = storiesId;
        this.storiesTitle = storiesTitle;
        this.storiesTags = storiesTags;
        this.storiesLanguage = storiesLanguage;
        this.storiesCoverImage = storiesCoverImage;
        this.storiesStoriesText = storiesStoriesText;
        this.storiesUploadedBy = storiesUploadedBy;
        this.storiesUserId = storiesUserId;
        this.storiesType = storiesType;
        this.storiesUploadedTime = storiesUploadedTime;
        this.storiesStatus = storiesStatus;
        this.storiesPinned = storiesPinned;
    }

    public String getStoriesTags() {
        return storiesTags;
    }

    public void setStoriesTags(String storiesTags) {
        this.storiesTags = storiesTags;
    }

    public String getStoriesLanguage() {
        return storiesLanguage;
    }

    public void setStoriesLanguage(String storiesLanguage) {
        this.storiesLanguage = storiesLanguage;
    }

    public String getStoriesCoverImage() {
        return storiesCoverImage;
    }

    public void setStoriesCoverImage(String storiesCoverImage) {
        this.storiesCoverImage = storiesCoverImage;
    }

    public String getStoriesId() {
        return storiesId;
    }

    public void setStoriesId(String storiesId) {
        this.storiesId = storiesId;
    }

    public String getStoriesTitle() {
        return storiesTitle;
    }

    public void setStoriesTitle(String storiesTitle) {
        this.storiesTitle = storiesTitle;
    }

    public String getStoriesStoriesText() {
        return storiesStoriesText;
    }

    public void setStoriesStoriesText(String storiesStoriesText) {
        this.storiesStoriesText = storiesStoriesText;
    }

    public String getStoriesUploadedBy() {
        return storiesUploadedBy;
    }

    public void setStoriesUploadedBy(String storiesUploadedBy) {
        this.storiesUploadedBy = storiesUploadedBy;
    }

    public String getStoriesUserId() {
        return storiesUserId;
    }

    public void setStoriesUserId(String storiesUserId) {
        this.storiesUserId = storiesUserId;
    }

    public String getStoriesType() {
        return storiesType;
    }

    public void setStoriesType(String storiesType) {
        this.storiesType = storiesType;
    }

    public String getStoriesUploadedTime() {
        return storiesUploadedTime;
    }

    public void setStoriesUploadedTime(String storiesUploadedTime) {
        this.storiesUploadedTime = storiesUploadedTime;
    }

    public String getStoriesStatus() {
        return storiesStatus;
    }

    public void setStoriesStatus(String storiesStatus) {
        this.storiesStatus = storiesStatus;
    }

    public String getStoriesPinned() {
        return storiesPinned;
    }

    public void setStoriesPinned(String storiesPinned) {
        this.storiesPinned = storiesPinned;
    }
}
