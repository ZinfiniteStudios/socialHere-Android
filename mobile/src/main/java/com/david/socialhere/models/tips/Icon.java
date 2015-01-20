
package com.david.socialhere.models.tips;
import com.google.gson.annotations.Expose;

public class Icon {

    @Expose
    private String prefix;
    @Expose
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
