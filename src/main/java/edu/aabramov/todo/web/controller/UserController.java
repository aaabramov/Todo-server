package edu.aabramov.todo.web.controller;

import edu.aabramov.todo.core.model.User;
import edu.aabramov.todo.service.UserService;
import edu.aabramov.todo.web.controller.annotation.JsonRestController;
import edu.aabramov.todo.web.dto.UserDetailsDto;
import edu.aabramov.todo.web.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrii Abramov on 11/24/16.
 */
@JsonRestController
public class UserController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    private final UserService userService;
    private final ModelMapper modelMapper;
    
    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        LOGGER.debug("UserController init");
        this.userService = userService;
    }
    
    @GetMapping(path = "/users")
    public List<UserDto> getAllUsers() {
        LOGGER.debug("all users requested");
        return userService.getAllUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping(path = "/users/{userId}")
    public UserDto getUser(@PathVariable("userId") String userId) {
        LOGGER.debug("all users requested");
        User foundUser = userService.getUser(userId);
        return modelMapper.map(foundUser, UserDto.class);
    }
    
    @GetMapping(path = "/users/{userId}/details")
    public UserDetailsDto getUserDetails(@PathVariable("userId") String userId) {
        LOGGER.debug("user details {} requested", userId);
        return modelMapper.map(userService.getUserDetails(userId), UserDetailsDto.class);
    }
    
}
