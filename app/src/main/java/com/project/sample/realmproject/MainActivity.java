package com.project.sample.realmproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.sample.realmproject.controller.UsersController;
import com.project.sample.realmproject.controller.callbacks.SaveUserCallback;
import com.project.sample.realmproject.controller.models.Users;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SaveUserCallback {

    private static final String TAG = MainActivity.class.getSimpleName();

    private UsersController usersController;

    private Button buttonSave;
    private Button buttonShowUsers;
    private EditText editName;
    private EditText editGender;

    private String name;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersController = UsersController.with(this);

        // Initialize Views
        buttonSave = (Button) findViewById(R.id.button_save);
        buttonShowUsers = (Button) findViewById(R.id.button_show_users);
        editName = (EditText) findViewById(R.id.edit_name);
        editGender = (EditText) findViewById(R.id.edit_gender);

        buttonSave.setOnClickListener(this);
        buttonShowUsers.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_save: {
                name = editName.getText().toString();
                gender = editGender.getText().toString();

                if(!TextUtils.isEmpty(name)) {
                    Users users = new Users();
                    users.setName(name);
                    users.setGender(gender);

                    usersController.saveUser(users, this);
                }

                break;
            }
            case R.id.button_show_users: {
                usersController.getAllUsers();
                break;
            }
        }
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