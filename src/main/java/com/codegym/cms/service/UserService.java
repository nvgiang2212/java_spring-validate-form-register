package com.codegym.cms.service;

import com.codegym.cms.model.User;

public interface UserService {
    Iterable<User> findAll();

    User findById(Long id);

    void save(User user);

    void remove(Long id);
}
