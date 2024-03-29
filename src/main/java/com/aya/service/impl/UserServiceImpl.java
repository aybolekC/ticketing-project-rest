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

//        return super.findAll().stream().filter(m->m.getRole().getDescription().equals("Manager")).collect(Collectors.toList()); -- my solution

        return super.findAll().stream().filter(m->m.getRole().getId()==2).collect(Collectors.toList());//instructors solution
    }

    @Override
    public List<UserDTO> findAllEmployees() {
        return super.findAll().stream().filter(m->m.getRole().getId()==3).collect(Collectors.toList());
    }
}
