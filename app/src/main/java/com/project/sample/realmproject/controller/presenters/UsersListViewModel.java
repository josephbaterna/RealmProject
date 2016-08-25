package com.project.sample.realmproject.controller.presenters;

import android.app.Application;

import com.project.sample.realmproject.controller.UsersController;
import com.project.sample.realmproject.controller.models.Users;
import com.project.sample.realmproject.controller.views.UsersListInteraction;

import java.util.List;

/**
 * Created by yoyo on 8/25/16.
 */
public class UsersListViewModel  extends Application {

    private UsersListInteraction mView;
    private List<Users> usersList;
    private UsersController usersController;

    public UsersListViewModel(UsersListInteraction view) {
        this.mView = view;

        usersController = UsersController.with(this);
    }

    public void getUsersList() {
        usersList = usersController.getAllUsers();

        if(!usersList.isEmpty()) mView.showUsersList(usersList);
        else mView.showNoUsers();
    }

}
