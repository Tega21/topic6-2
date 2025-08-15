package com.gcu.data;

import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service

public class UsersDataService implements UsersDataAccessInterface<UserEntity>, DataAccessInterface<UserEntity> {

    private final UsersRepository usersRepository;

    public UsersDataService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override public List<UserEntity> findAll() { return Collections.emptyList(); }
    @Override public UserEntity findById(String id) { return null; } // match your interface signature
    @Override public boolean create(UserEntity t) { return false; }
    @Override public boolean update(UserEntity t) { return false; }
    @Override public boolean delete(UserEntity t) { return false; }
}