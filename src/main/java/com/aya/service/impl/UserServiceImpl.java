package com.aya.service.impl;

import com.aya.dto.UserDTO;
import com.aya.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void update(UserDTO object) {
        super.update(object.getUserName(), object);
    }

    @Override
    public UserDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<UserDTO> findAllManagers() {
        List<UserDTO> users= super.findAll();
        List<UserDTO> managers=users.stream().filter(m->m.getRole().getDescription().equals("Manager")).collect(Collectors.toList());
        return managers;
    }
}
