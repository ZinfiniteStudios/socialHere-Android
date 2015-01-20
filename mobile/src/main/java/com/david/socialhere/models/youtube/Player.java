
package com.david.socialhere.models.youtube;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("default")
    @Expose
    private String _default;
    @Expose
    private String mobile;

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
