package com.aya.service.impl;

import com.aya.dto.UserDTO;
import com.aya.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractMapService<String, UserDTO> implements UserService {
    @Override
    public UserDTO save(UserDTO object) {
        return super.save(object.getUserName(), object);
    }

    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String id) {

        super.deleteById(id);

    }

    @Override
    public UserDTO findById(String id) {
        return super.findById(id);
    }
}
