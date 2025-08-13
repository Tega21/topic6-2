package com.gcu.data;

import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UsersRepository;
import org.springframework.stereotype.Service;

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

    // The guide only requires findByUsername() for now.
    // Stub the rest to keep the compiler happy.
    @Override public List<UserEntity> findAll() { return List.of(); }
    @Override public UserEntity findById(int id) { return null; }
    @Override public boolean create(UserEntity t) { return false; }
    @Override public boolean update(UserEntity t) { return false; }
    @Override public boolean delete(UserEntity t) { return false; }
}
