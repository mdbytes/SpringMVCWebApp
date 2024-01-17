package com.mdbytes.club.service;

import com.mdbytes.club.model.Role;

public interface RoleService {

    void delete(Role role);

    Role findByName(String userRole);
}
