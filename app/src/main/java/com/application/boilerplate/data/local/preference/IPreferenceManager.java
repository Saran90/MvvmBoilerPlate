package com.application.boilerplate.data.local.preference;

/**
 * Created by SARAN M S on 26/07/19.
 */

public interface IPreferenceManager {

    String getToken();

    void setToken(String token);

    String getAccessToken();

    void setAccessToken(String token);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    void clearAll();

}

