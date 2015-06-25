package com.github.bilak.apptemplate.service;

import com.github.bilak.apptemplate.domain.Role;
import com.github.bilak.apptemplate.domain.User;

import java.util.List;

public interface IUserService extends IBaseService<User>{

    User getByEmail(String email);

    List<Role> getAllRoles(User user);
}
