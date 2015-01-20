
package com.david.socialhere.models.twitter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("iso_language_code")
    @Expose
    private String isoLanguageCode;
    @SerializedName("result_type")
    @Expose
    private String resultType;

    /**
     * 
     * @return
     *     The isoLanguageCode
     */
    public String getIsoLanguageCode() {
        return isoLanguageCode;
    }

    /**
     * 
     * @param isoLanguageCode
     *     The iso_language_code
     */
    public void setIsoLanguageCode(String isoLanguageCode) {
        this.isoLanguageCode = isoLanguageCode;
    }

    /**
     * 
     * @return
     *     The resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * 
     * @param resultType
     *     The result_type
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

}
