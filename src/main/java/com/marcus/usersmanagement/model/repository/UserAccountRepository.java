package com.marcus.usersmanagement.model.repository;

import com.marcus.usersmanagement.model.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, String> {
    Optional<UserAccountEntity> findByUsername(String username);
}
