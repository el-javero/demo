package com.alfinbanco.demo.repository;

import com.alfinbanco.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
}
