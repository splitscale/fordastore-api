package com.splitscale.api.fordastore.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserControllerImpl implements UserController {
  private UserDAO dao;

  @Autowired
  public UserControllerImpl(UserDAO dao) {
    this.dao = dao;
  }

  @Override
  @ResponseBody
  @GetMapping(value = "/user/get")
  public UserInfo getUser(@RequestParam("username") String JSONgetPayload) {
    return dao.readUser(JSONgetPayload);
  }

  @Override
  @GetMapping(value = "/user/add")
  public void addUser(String JSONpostPayload) {
    dao.createUser("username1", "password1");
  }

  @Override
  @GetMapping(value = "/user/delete")
  public void deleteUser(String JSONpostPayload) {
    dao.deleteUser("username2", "password2");
  }

  @Override
  @GetMapping(value = "/user/update")
  public void updateUser(String JSONpostPayload) {
    dao.updateUser("username3", "password3");
  }
}
