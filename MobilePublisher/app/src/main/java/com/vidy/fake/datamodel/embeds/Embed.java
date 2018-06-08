package com.vidy.fake.datamodel.embeds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Embed {

    @SerializedName("appid")
    @Expose
    private String appid;
    @SerializedName("postid")
    @Expose
    private String postid;
    @SerializedName("clipid")
    @Expose
    private String clipid;
    @SerializedName("phrase")
    @Expose
    private String phrase;

    public Embed(String appid, String postid, String clipid, String phrase) {
        this.appid = appid;
        this.postid = postid;
        this.clipid = clipid;
        this.phrase = phrase;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getClipid() {
        return clipid;
    }

    public void setClipid(String clipid) {
        this.clipid = clipid;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

}