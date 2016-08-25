package com.project.sample.realmproject.controller;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.util.Log;

import com.project.sample.realmproject.controller.callbacks.SaveUserCallback;
import com.project.sample.realmproject.controller.models.Users;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yoyo on 8/24/16.
 */
public class UsersController {

    private static final String TAG = UsersController.class.getSimpleName();

    private static UsersController instance;
    private final Realm realm;

    public UsersController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static UsersController with(Activity activity) {
        if(instance == null) {
            instance = new UsersController(activity.getApplication());
        }

        return instance;
    }

    public static UsersController with(Fragment fragment) {
        if(instance == null) {
            instance = new UsersController(fragment.getActivity().getApplication());
        }

        return instance;
    }

    public static UsersController with(Application application) {
        if(instance == null) {
            instance = new UsersController(application);
        }

        return instance;
    }

    public static UsersController getInstance() {
        return instance;
    }

    public void saveUser(final Users users, final SaveUserCallback callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Users entity = realm.copyToRealmOrUpdate(users);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.saveUser(true);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callback.saveUser(false);
            }
        });
    }

    public String getUserName(String name) {
        Users user = realm.where(Users.class)
                                        .equalTo(Users.COLUMN_NAME, name)
                                        .findFirst();

        if(user != null) return user.getName();
        else return "";
    }

    public List<Users> getAllUsers() {
        return realm.where(Users.class).findAll();
    }
}
