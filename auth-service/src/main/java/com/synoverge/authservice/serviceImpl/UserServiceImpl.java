package com.synoverge.authservice.serviceImpl;

import com.synoverge.authservice.model.entity.UserInfo;
import com.synoverge.authservice.repository.UserInfoRepository;
import com.synoverge.authservice.service.UserService;
import com.synoverge.common.utility.Constants;
import com.synoverge.common.utility.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public UserServiceImpl() {
    }

    @Override
    public UserInfo createUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        return repository.save(userInfo);
    }

    @Override
    public List<UserInfo> listOfUsers() {
       List<UserInfo> listOfUsers = repository.findAll();
       return listOfUsers;
    }

    @Override
    public UserInfo getUserById(int userId) {
        UserInfo userInfo = repository.findById(userId).orElseThrow(() -> new NoSuchElementException(ErrorMessages.CUSTOMER_NOT_FOUND));
        return userInfo;
    }
}
