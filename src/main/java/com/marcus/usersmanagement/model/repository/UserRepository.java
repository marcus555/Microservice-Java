package com.marcus.usersmanagement.model.repository;

import com.marcus.usersmanagement.model.entity.RoleEntity;
import com.marcus.usersmanagement.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Page<UserEntity> findDistinctByAccountRolesIn(Set<RoleEntity> roleEntities, Pageable pageable);
    Page<UserEntity> findAllDistinctByAccountRolesIn(Set<RoleEntity> roleEntities, Pageable pageable);

}
