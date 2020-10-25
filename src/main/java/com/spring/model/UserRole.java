package com.spring.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Transient
@Component
public class UserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roleSet")
    private Set<User> userSet = new HashSet<>();

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public UserRole() {

    }

    public UserRole(Long id) {
        this.id = id;
    }

    public UserRole(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getRole_id() {
        return id;
    }

    public void setRole_id(Long role_id) {
        this.id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRole role = (UserRole) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 7;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}