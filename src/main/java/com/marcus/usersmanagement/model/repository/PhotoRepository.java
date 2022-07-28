package com.marcus.usersmanagement.model.repository;

import com.marcus.usersmanagement.model.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, String> {

    List<PhotoEntity> findByUserId(String userId);

    PhotoEntity findByUserIdAndActive(String userId, boolean active);
}
