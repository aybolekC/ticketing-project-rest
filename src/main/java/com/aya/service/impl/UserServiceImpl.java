package com.aya.service.impl;

import com.aya.dto.UserDTO;
import com.aya.entity.User;
import com.aya.mapper.RoleMapper;
import com.aya.mapper.UserMapper;
import com.aya.repository.RoleRepository;
import com.aya.repository.UserRepository;
import com.aya.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {

        return userRepository.findAll().stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {


        return userMapper.convertToDTO(userRepository.findByUserName(username));
    }

    @Override
    public void save(UserDTO userDTO) {

      userRepository.save(userMapper.convertToEntity(userDTO));

    }

    @Override
    public UserDTO update(UserDTO userDTO) {

        //find current user
        User user=userRepository.findByUserName(userDTO.getUserName());

        //convert user to entity
        User convertedUser=userMapper.convertToEntity(userDTO);

        //set id to converted obj
        convertedUser.setId(user.getId());

        //save updated user
        userRepository.save(convertedUser);

        return findByUserName(userDTO.getUserName());
    }


    @Override
    public void deleteByUserName(String username) {

        userRepository.deleteByUserName(username);

    }

    @Override
    public void delete(String username) {

        User user=userRepository.findByUserName(username);
        user.setIsDeleted(true);
        userRepository.save(user);



    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> users=userRepository.findAllByRoleDescriptionIgnoreCase(role);
        return users.stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }
}
