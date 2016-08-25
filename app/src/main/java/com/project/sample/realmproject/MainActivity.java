package com.project.sample.realmproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.sample.realmproject.activities.UsersListActivity;
import com.project.sample.realmproject.controller.UsersController;
import com.project.sample.realmproject.controller.callbacks.SaveUserCallback;
import com.project.sample.realmproject.controller.models.Users;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SaveUserCallback {

    private static final String TAG = MainActivity.class.getSimpleName();

    private UsersController usersController;

    @BindView(R.id.button_save) Button buttonSave;
    @BindView(R.id.button_show_users) Button buttonShowUsers;
    @BindView(R.id.edit_name) EditText editName;
    @BindView(R.id.edit_gender) EditText editGender;

    private String name;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        usersController = UsersController.with(this);

    }

    @OnClick(R.id.button_save)
    public void saveUsers() {
        name = editName.getText().toString();
        gender = editGender.getText().toString();

        if(!TextUtils.isEmpty(name)) {
            Users users = new Users();
            users.setName(name);
            users.setGender(gender);

            usersController.saveUser(users, this);
        }
    }

    @OnClick(R.id.button_show_users)
    public void showUsers() {
        Intent intent = UsersListActivity.getStartIntent(this);
        startActivity(intent);
    }

    @Override
    public void saveUser(boolean result) {
        if(result) {
            Log.d(TAG, "Success");
            showToast();
        } else {
            Log.d(TAG, "Faild");
        }
    }

    private void showToast() {
        Toast.makeText(this, "User : " + usersController.getUserName(name), Toast.LENGTH_SHORT).show();
    }
}