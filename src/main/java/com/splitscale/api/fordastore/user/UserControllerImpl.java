package com.splitscale.api.fordastore.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {
  private UserDAO dao;

  @Autowired
  public UserControllerImpl(UserDAO dao) {
    this.dao = dao;
  }

  @Override
  @ResponseBody
  @GetMapping(value = "/get")
  public UserInfo getUser(@RequestParam("username") String JSONgetPayload) {
    return dao.readUser(JSONgetPayload);
  }

  @Override
  @PostMapping(value = "/add")
  public void addUser(@RequestBody UserInfoImpl userInfo) {
    dao.createUser(userInfo.getUsername(), userInfo.getPassword());
  }

  @Override
  @DeleteMapping(value = "/delete")
  public void deleteUser(UserInfoImpl userInfo) {
    dao.deleteUser(userInfo.getUsername(), userInfo.getPassword());
  }

  @Override
  @PutMapping(value = "/update")
  public void updateUser(UserInfoImpl userInfo) {
    dao.updateUser(userInfo.getUsername(), userInfo.getPassword());
  }
}
