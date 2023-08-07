package com.example.shopbangiay.repository;

import com.example.shopbangiay.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}
