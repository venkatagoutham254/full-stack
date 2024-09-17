package com.aforo.project.service;

import com.aforo.project.dto.UserDTO;
import com.aforo.project.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    List<UserDTO> getAllUsers();
	Optional<User> findByEmail(String email);
	
}

