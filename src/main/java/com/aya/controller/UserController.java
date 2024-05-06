package com.aya.controller;

import com.aya.annotation.DefaultExceptionMessage;
import com.aya.dto.UserDTO;
import com.aya.entity.ResponseWrapper;
import com.aya.exception.TicketingProjectException;
import com.aya.service.RoleService;
import com.aya.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "UserController", description = "User API")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @RolesAllowed("Admin")
    @Operation(summary = "Get Users")
    public ResponseEntity<ResponseWrapper> retrieveAllUsers() {
        List<UserDTO> users = userService.listAllUsers();
        return ResponseEntity.ok(
                new ResponseWrapper("Users are successfully retrieved", users, HttpStatus.OK));
    }

    @GetMapping("/{username}")
    @RolesAllowed("Admin")
    @Operation(summary = "Get User By Username")
    public ResponseEntity<ResponseWrapper> getUserByUserName(@PathVariable("username") String username) {
        UserDTO user = userService.findByUserName(username);
        return ResponseEntity.ok(
                new ResponseWrapper("User is successfully retrieved", user, HttpStatus.OK));
    }


//    {
//        "firstName": "Test1",
//            "lastName": "Test2",
//            "userName": "test@gmail.com",
//            "passWord": "test123",
//            "gender": "MALE",
//            "phone": "0123456789",
//            "enabled": true,
//            "role": {
//        "id": 2,
//                "description": "Manager"
//    }
//    }
//

    @PostMapping()
    @RolesAllowed("Admin")
    @Operation(summary = "Create User")
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper("User is successfully created", HttpStatus.CREATED));
    }

    @PutMapping()
    @RolesAllowed("Admin")
    @Operation(summary = "Update User")
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO user) {
        userService.update(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper("User is successfully updated", user, HttpStatus.CREATED));
    }

    @DeleteMapping("/{username}")
    @RolesAllowed("Admin")
    @Operation(summary = "Delete User")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete user")
    public ResponseEntity<ResponseWrapper> deleteUserByUserName(@PathVariable("username") String username) throws TicketingProjectException {
        userService.delete(username);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT)
//                .body(new ResponseWrapper("User is successfully deleted", HttpStatus.OK));

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseWrapper("User is successfully deleted", HttpStatus.OK));
    }

}
