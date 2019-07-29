package com.application.boilerplate.data;

import android.content.Context;

import com.application.boilerplate.data.local.db.DbManager;
import com.application.boilerplate.data.local.db.IDbManager;
import com.application.boilerplate.data.local.preference.PreferenceManager;
import com.application.boilerplate.data.remote.RemoteDataManager;
import com.google.gson.Gson;

import javax.inject.Inject;

public class DataManager implements IDataManager{

    private RemoteDataManager remoteDataManager;
    private PreferenceManager preferenceManager;
    private Context context;
    private IDbManager dbManager;

    @Inject
    public DataManager(Context context, IDbManager dbManager, PreferenceManager preferenceManager,
                          RemoteDataManager remoteDataManager) {
        this.context = context;
        this.dbManager = dbManager;
        this.preferenceManager = preferenceManager;
        this.remoteDataManager = remoteDataManager;
    }


    public RemoteDataManager getRemoteDataManager() {
        return remoteDataManager;
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            Long userId) {

        preferenceManager.setAccessToken(accessToken);
        preferenceManager.setCurrentUserId(userId);

        updateApiHeader(userId, accessToken);
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        getRemoteDataManager().getApiHeader().getProtectedApiHeader().setUserId(userId);
        getRemoteDataManager().getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    public IDbManager getDbManager() {
        return dbManager;
    }

    public PreferenceManager getPreferenceManager() {
        return preferenceManager;
    }
}
