package com.application.boilerplate.data.remote.login;

import com.application.boilerplate.data.model.api.login.LoginResponse;
import com.application.boilerplate.data.remote.ApiEndPoint;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.application.boilerplate.utils.NetworkUtils.NETWORK_AUTHORIZATION;

public interface AuthService {

    @POST(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
    @FormUrlEncoded
    Observable<Response<LoginResponse>> loginUser(@Field("username") String username,
                                                  @Field("password") String password,
                                                  @Field("grant_type") String grant_type,
                                                  @Header(NETWORK_AUTHORIZATION) String authToken);

}
