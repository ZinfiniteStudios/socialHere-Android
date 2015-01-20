
package com.david.socialhere.models.youtube.loc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("5")
    @Expose
    private String _5;
    @SerializedName("6")
    @Expose
    private String _6;

    /**
     * 
     * @return
     *     The _1
     */
    public String get1() {
        return _1;
    }

    /**
     * 
     * @param _1
     *     The 1
     */
    public void set1(String _1) {
        this._1 = _1;
    }

    /**
     * 
     * @return
     *     The _5
     */
    public String get5() {
        return _5;
    }

    /**
     * 
     * @param _5
     *     The 5
     */
    public void set5(String _5) {
        this._5 = _5;
    }

    /**
     * 
     * @return
     *     The _6
     */
    public String get6() {
        return _6;
    }

    /**
     * 
     * @param _6
     *     The 6
     */
    public void set6(String _6) {
        this._6 = _6;
    }

}
