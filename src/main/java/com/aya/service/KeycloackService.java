package com.aya.service;

import com.aya.dto.UserDTO;

import javax.ws.rs.core.Response;

public interface KeycloackService {

    Response userCreate(UserDTO userDTO);
    void delete(String username);
}
