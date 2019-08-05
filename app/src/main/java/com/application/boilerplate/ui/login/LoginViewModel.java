/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.application.boilerplate.ui.login;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.application.boilerplate.data.DataManager;
import com.application.boilerplate.data.model.api.login.LoginRequest;
import com.application.boilerplate.data.model.api.login.LoginResponse;
import com.application.boilerplate.data.remote.rx.SchedulerProvider;
import com.application.boilerplate.ui.base.BaseViewModel;
import com.application.boilerplate.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import androidx.lifecycle.MutableLiveData;
import okhttp3.Credentials;

/**
 * Created by amitshekhar on 08/07/17.
 */

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    private MutableLiveData<LoginResponse> loginResponse;

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public boolean isEmailAndPasswordValid(String email, String password) {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        if (!CommonUtils.isEmailValid(email)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public MutableLiveData<LoginResponse> getLoginResponse() {
        if (loginResponse==null)
            loginResponse = new MutableLiveData<>();
        return loginResponse;
    }

    public void login(String username, String password) {
        setIsLoading(true);
        String auth = Credentials.basic("citta", "citta");
        Log.d("TAG", auth);
        getCompositeDisposable().add(getDataManager()
                .getRemoteDataManager().getAuthRepository()
                .doServerLoginApiCall(new LoginRequest(username, password,
                        "password"), auth)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    if (response.code()==200){
                        loginResponse.setValue(response.body());
                        getNavigator().showMessage("Success!");
                        getDataManager().updateUserInfo(
                                response.body().getAccessToken(),
                                Long.valueOf(response.body().getUserId())
                        );
                        getNavigator().openMainActivity();
                    }else if (response.code()==400){
                        getNavigator().showMessage("Invalid user!");
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void onServerLoginClick() {
        getNavigator().login();
    }
}
