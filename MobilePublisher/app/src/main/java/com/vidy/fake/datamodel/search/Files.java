
package com.vidy.fake.datamodel.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Files {

    @SerializedName("landscapeImage240")
    @Expose
    private LandscapeImage240 landscapeImage240;
    @SerializedName("landscapeVideo240")
    @Expose
    private LandscapeVideo240 landscapeVideo240;

    public LandscapeImage240 getLandscapeImage240() {
        return landscapeImage240;
    }

    public void setLandscapeImage240(LandscapeImage240 landscapeImage240) {
        this.landscapeImage240 = landscapeImage240;
    }

    public LandscapeVideo240 getLandscapeVideo240() {
        return landscapeVideo240;
    }

    public void setLandscapeVideo240(LandscapeVideo240 landscapeVideo240) {
        this.landscapeVideo240 = landscapeVideo240;
    }

}
