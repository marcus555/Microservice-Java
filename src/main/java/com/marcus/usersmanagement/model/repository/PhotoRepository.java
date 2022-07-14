package com.marcus.usersmanagement.model.repository;

import com.marcus.usersmanagement.model.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {

}
