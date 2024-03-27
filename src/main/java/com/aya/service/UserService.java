package com.aya.service;


import com.aya.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends CrudService<UserDTO, String> {

    List<UserDTO> findAllManagers();




}
