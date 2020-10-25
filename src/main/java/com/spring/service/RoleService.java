package com.spring.service;

import com.spring.model.UserRole;

import java.util.Set;

public interface RoleService {
    Set<UserRole> getRoleSet(Set<String> roles);
    UserRole getDefaultRole();

}
