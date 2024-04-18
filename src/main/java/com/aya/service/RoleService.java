package com.aya.service;

import com.aya.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> listAllRoles();
    RoleDTO findByRoleId(Long id);
}
