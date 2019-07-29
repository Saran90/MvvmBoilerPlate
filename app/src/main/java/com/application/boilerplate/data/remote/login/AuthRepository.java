package com.application.boilerplate.data.remote.login;

import com.application.boilerplate.data.model.api.login.LoginRequest;
import com.application.boilerplate.data.model.api.login.LoginResponse;
import com.application.boilerplate.data.remote.ApiEndPoint;
import com.application.boilerplate.data.remote.ApiHeader;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthRepository implements IAuthRepository {

    private ApiHeader mApiHeader;
    private Retrofit retrofit;
    private AuthService authService;

    @Inject
    public AuthRepository(ApiHeader apiHeader, Retrofit retrofit, AuthService authService) {
        this.mApiHeader = apiHeader;
        this.retrofit = retrofit;
        this.authService = authService;
    }

    @Override
    public Observable<Response<LoginResponse>> doServerLoginApiCall(LoginRequest request, String token) {
        return authService.loginUser(request.getUsername(),request.getPassword(),
                request.getGrantType(),token);
    }
}
