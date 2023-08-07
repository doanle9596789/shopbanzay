package com.example.shopbangiay.service.role;

import com.example.shopbangiay.model.Roles;
import com.example.shopbangiay.service.IGeneralService;

import java.util.Optional;
import java.util.Set;

public interface IRoleService extends IGeneralService<Roles> {
    Iterable<Roles> findAll();

    Optional<Roles> findById(Long id);

    Roles save(Roles roles);

    void remove(Long id);

    Roles findByName(String name);
    Set<Roles> getRolesByName(Set<String> roleNames);
}
