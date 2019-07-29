package com.application.boilerplate.ui.login;

public interface LoginNavigator {

    void handleError(Throwable throwable);

    void login();

    void openMainActivity();

    void showMessage(String message);
}