package com.spring.service;


import com.spring.model.User;
import com.spring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserDetailsService, UserService{

    @PersistenceContext
    EntityManager entityManager;

    UserRepo userRepo;

    @Autowired
    UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Transactional
    @Override
    public void addUser(User user) {
        userRepo.save(user);
    }
    @Transactional
    @Override
    public List<User> listUsers() {
        return userRepo.findAll();
    }
    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
    @Transactional
    @Override
    public User getUserById(Long id) {
        return userRepo.getOne(id);
    }
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
