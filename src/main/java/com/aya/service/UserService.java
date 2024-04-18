package com.aya.service;


import com.aya.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void deleteByUserName(String username);
    void delete(String username);
    List<UserDTO> listAllByRole(String role);





}
