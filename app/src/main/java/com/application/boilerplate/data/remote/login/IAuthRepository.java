package com.application.boilerplate.data.remote.login;

import com.application.boilerplate.data.model.api.login.LoginRequest;
import com.application.boilerplate.data.model.api.login.LoginResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;

public interface IAuthRepository {
    Observable<Response<LoginResponse>> doServerLoginApiCall(LoginRequest request, String token);
}
