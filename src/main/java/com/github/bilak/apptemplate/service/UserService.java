package com.github.bilak.apptemplate.service;

import com.github.bilak.apptemplate.domain.Role;
import com.github.bilak.apptemplate.domain.User;
import com.github.bilak.apptemplate.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User> implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User get(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public List<Role> getAllRoles(User user) {
        return userRepository.getUserRoles(user.getId());
    }
}
