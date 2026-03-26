package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}