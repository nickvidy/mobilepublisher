
package com.vidy.fake.datamodel.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LandscapeVideo240 {

    @SerializedName("mimetype")
    @Expose
    private String mimetype;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("size")
    @Expose
    private int size;
    @SerializedName("url")
    @Expose
    private String url;

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
