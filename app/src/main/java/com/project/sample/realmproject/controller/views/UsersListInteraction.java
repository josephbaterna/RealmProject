package com.project.sample.realmproject.controller.views;

import com.project.sample.realmproject.controller.models.Users;

import java.util.List;

/**
 * Created by yoyo on 8/25/16.
 */
public interface UsersListInteraction {

    void showUsersList(List<Users> users);

    void showNoUsers();

}
