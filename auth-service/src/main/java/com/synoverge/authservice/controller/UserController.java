package com.synoverge.authservice.controller;

import com.synoverge.authservice.model.entity.UserInfo;
import com.synoverge.authservice.securityService.UserInfoService;
import com.synoverge.authservice.serviceImpl.JwtService;
import com.synoverge.authservice.serviceImpl.UserServiceImpl;
import com.synoverge.common.dtos.BaseResponseEntity;
import com.synoverge.common.utility.Constants;
import com.synoverge.common.utility.SuccessMessage;
import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.baseUrl)
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("user/register")
    public ResponseEntity<BaseResponseEntity> createUser(@RequestBody UserInfo userInfo) {
        UserInfo userDetails = userService.createUser(userInfo);
        BaseResponseEntity baseResponse = new BaseResponseEntity(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), SuccessMessage.REGISTER_SUCCESSFULLY, userDetails);
        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }

    @GetMapping("user/list")
    public ResponseEntity<BaseResponseEntity> listOfAllUsers() {
        List<UserInfo> users = userService.listOfUsers();
        BaseResponseEntity baseResponse = new BaseResponseEntity(HttpStatus.OK.value(), HttpStatus.OK.name(), SuccessMessage.SUCCESS, users);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<BaseResponseEntity> getUserById(@PathParam("userId") int userId) {
        UserInfo users = userService.getUserById(userId);
        BaseResponseEntity baseResponse = new BaseResponseEntity(HttpStatus.OK.value(), HttpStatus.OK.name(), SuccessMessage.SUCCESS, users);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
