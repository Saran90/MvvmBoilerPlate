package com.application.boilerplate.data.remote;

import com.application.boilerplate.data.remote.login.AuthRepository;

public interface IRemoteDataManager {

    AuthRepository getAuthRepository();


    ApiHeader getApiHeader();
}
