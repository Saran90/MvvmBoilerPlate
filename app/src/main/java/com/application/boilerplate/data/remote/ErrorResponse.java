package com.application.boilerplate.data.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SARAN M S on 26/07/19.
 */

public class ErrorResponse {
    @SerializedName("projectStatusCode")
    String projectStatusCode;

    public String getProjectStatusCode() {
        return projectStatusCode;
    }

    public void setProjectStatusCode(String projectStatusCode) {
        this.projectStatusCode = projectStatusCode;
    }
}
