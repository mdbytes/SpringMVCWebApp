package com.mdbytes.club.service.implementation;

import com.mdbytes.club.model.Role;
import com.mdbytes.club.repository.RoleRepository;
import com.mdbytes.club.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Role findByName(String userRole) {
        return roleRepository.findByName(userRole);
    }
}
