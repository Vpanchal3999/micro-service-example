package com.synoverge.authservice.repository;

import com.synoverge.authservice.model.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth,Long> {
}
