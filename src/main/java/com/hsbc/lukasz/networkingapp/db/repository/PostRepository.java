package com.hsbc.lukasz.networkingapp.db.repository;

import com.hsbc.lukasz.networkingapp.db.model.DbPost;
import com.hsbc.lukasz.networkingapp.db.model.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<DbPost, Integer> {

    List<DbPost> findAllByUser(DbUser user);

    List<DbPost> findAllByUserIn(List<DbUser> users);
}
