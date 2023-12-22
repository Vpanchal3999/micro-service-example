package com.synoverge.authservice.service;

import com.synoverge.authservice.model.entity.UserInfo;

import java.util.List;

public interface UserService {
    UserInfo createUser(UserInfo user);

    List<UserInfo> listOfUsers();

    UserInfo getUserById(int id);
}
