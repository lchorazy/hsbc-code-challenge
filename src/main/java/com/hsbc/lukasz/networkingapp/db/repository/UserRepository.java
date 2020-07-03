package com.hsbc.lukasz.networkingapp.db.repository;

import com.hsbc.lukasz.networkingapp.db.model.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<DbUser, Integer> {

    default DbUser getUser(String username) {
        return this
                .findByUsername(username)
                .orElseGet(() -> this.save(new DbUser(username)));
    }

    Optional<DbUser> findByUsername(String username);
}
