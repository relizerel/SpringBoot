package com.spring.service;

import com.spring.model.UserRole;
import com.spring.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class RoleServiceImp implements RoleService {

    //@Value("${defaultRoleName}")
    @Value("USER")
    private final String defaultRoleName = "USER";

    @Autowired
    public RoleRepo roleRepo;

    @Override
    public Set<UserRole> getRoleSet(Set<String> roles) {
        return roleRepo.getRolesByNameIn(roles);
    }

    @Override
    public UserRole getDefaultRole() {
        return roleRepo.getRoleByName(defaultRoleName);
    }
}
