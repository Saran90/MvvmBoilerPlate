package com.application.boilerplate.data.remote;

import com.application.boilerplate.data.remote.login.AuthRepository;

import javax.inject.Inject;

public class RemoteDataManager implements IRemoteDataManager{

    private ApiHeader apiHeader;
    private AuthRepository authRepository;

    @Inject
    public RemoteDataManager(ApiHeader apiHeader,AuthRepository authRepository) {
        this.apiHeader = apiHeader;
        this.authRepository = authRepository;
    }

    @Override
    public AuthRepository getAuthRepository() {
        return authRepository;
    }

    @Override
    public ApiHeader getApiHeader() {
        return apiHeader;
    }
}
