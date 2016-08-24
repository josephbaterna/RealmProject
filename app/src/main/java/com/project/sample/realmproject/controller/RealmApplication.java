package com.project.sample.realmproject.controller;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yoyo on 8/24/16.
 */
public class RealmApplication extends Application {

    private static final String TAG = RealmApplication.class.getSimpleName();

    private static final String REAL_NAME = "sample.realm";
    private static final long REALM_VERSION = 1;

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name(REAL_NAME)
                .schemaVersion(REALM_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
