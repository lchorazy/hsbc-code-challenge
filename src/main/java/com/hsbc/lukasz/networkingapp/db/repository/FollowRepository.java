package com.hsbc.lukasz.networkingapp.db.repository;

import com.hsbc.lukasz.networkingapp.db.model.DbFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<DbFollow, Integer> {
}
