package com.project.sample.realmproject.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.sample.realmproject.R;
import com.project.sample.realmproject.adapters.UsersListAdapter;
import com.project.sample.realmproject.controller.models.Users;
import com.project.sample.realmproject.controller.presenters.UsersListViewModel;
import com.project.sample.realmproject.controller.views.UsersListInteraction;
import com.project.sample.realmproject.listeners.OnItemListListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmObject;

/**
 * Created by yoyo on 8/25/16.
 */
public class UsersListActivity extends AppCompatActivity implements UsersListInteraction, OnItemListListener {

    private static final String TAG = UsersListActivity.class.getSimpleName();

    private Activity activity;
    private UsersListAdapter usersListAdapter;
    private UsersListViewModel userViewModel;
    private Gson gson;

    @BindView(R.id.recycler_users_list)
    RecyclerView recyckerUsersList;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);

        this.activity = this;

        userViewModel = new UsersListViewModel(this);
        userViewModel.getUsersList();

        gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(RealmObject.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
    }

    /**
     * Provide intent to start this activity
     * @param context
     * @return
     */
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, UsersListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    public void showUsersList(List<Users> users) {
        usersListAdapter = new UsersListAdapter(activity, users, this);

        recyckerUsersList.setLayoutManager(new LinearLayoutManager(this));
        recyckerUsersList.setAdapter(usersListAdapter);
    }

    @Override
    public void showNoUsers() {

    }

    @Override
    public void onItemClick(Users user) {
        Log.d(TAG, "Users => " + user.toString());
    }
}
