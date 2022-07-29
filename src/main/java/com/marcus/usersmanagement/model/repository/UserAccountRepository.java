package com.marcus.usersmanagement.model.repository;

import com.marcus.usersmanagement.model.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, String> {
    UserAccountEntity findByUsername(String username);
}
