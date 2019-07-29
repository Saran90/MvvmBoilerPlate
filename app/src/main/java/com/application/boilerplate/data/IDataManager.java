package com.application.boilerplate.data;

public interface IDataManager {

    void updateUserInfo(
            String accessToken,
            Long userId);

    void updateApiHeader(Long userId, String accessToken);
}
