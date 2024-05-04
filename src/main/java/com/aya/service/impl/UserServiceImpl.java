package com.aya.service.impl;

import com.aya.dto.ProjectDTO;
import com.aya.dto.TaskDTO;
import com.aya.dto.UserDTO;
import com.aya.entity.User;
import com.aya.mapper.UserMapper;
import com.aya.repository.UserRepository;
import com.aya.service.ProjectService;
import com.aya.service.TaskService;
import com.aya.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProjectService projectService;
    private final TaskService taskService;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, ProjectService projectService, TaskService taskService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.projectService = projectService;
        this.taskService = taskService;
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
    public void save(UserDTO dto) {
        dto.setEnabled(true);
        User obj=userMapper.convertToEntity(dto);
//        obj.setPassWord(passwordEncoder.encode(obj.getPassWord()));
      //  obj.setPassWord("$2y$10$FLdtxzJ.F.DVaFE8.HZUK.CzjNUt6FiKVp0296zjpzhT6xnhUza86");
        userRepository.save(obj);

//      userRepository.save(userMapper.convertToEntity(dto));

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
        if (checkIfUserCanBeDeleted(user)){
            user.setIsDeleted(true);
            user.setUserName(user.getUserName()+"-"+user.getId());
            userRepository.save(user);
        }

    }

    private boolean checkIfUserCanBeDeleted(User user){

        switch (user.getRole().getDescription()){
            case "Manager":
                List<ProjectDTO> projectDTOList=projectService.readAllByAssignedManager(user);
                return projectDTOList.size()==0;
            case "Employee":
                List<TaskDTO> taskDTOList=taskService.readAllByAssignedEmployee(user);
                return taskDTOList.size()==0;

            default:
                return true;

        }


    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> users=userRepository.findAllByRoleDescriptionIgnoreCase(role);
        return users.stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }
}
