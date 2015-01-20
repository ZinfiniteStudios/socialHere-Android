
package com.david.socialhere.models.youtube.loc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("default")
    @Expose
    private String _default;
    @Expose
    private String mobile;

    /**
     * 
     * @return
     *     The _default
     */
    public String getDefault() {
        return _default;
    }

    /**
     * 
     * @param _default
     *     The default
     */
    public void setDefault(String _default) {
        this._default = _default;
    }

    /**
     * 
     * @return
     *     The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 
     * @param mobile
     *     The mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
