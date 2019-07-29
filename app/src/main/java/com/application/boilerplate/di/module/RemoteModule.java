package com.application.boilerplate.di.module;

import com.application.boilerplate.data.local.db.DbManager;
import com.application.boilerplate.data.local.db.IDbManager;
import com.application.boilerplate.data.remote.login.AuthRepository;
import com.application.boilerplate.data.remote.login.AuthService;
import com.application.boilerplate.data.remote.login.IAuthRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RemoteModule {

    @Provides
    @Singleton
    IAuthRepository provideAuthRepository(AuthRepository authRepository) {
        return authRepository;
    }

    @Provides
    @Singleton
    AuthService provideAuthService(Retrofit retrofit){
        return retrofit.create(AuthService.class);
    }
}
