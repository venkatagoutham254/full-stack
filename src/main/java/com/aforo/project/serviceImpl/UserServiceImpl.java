package com.aforo.project.serviceImpl;

import com.aforo.project.dto.UserDTO;
import com.aforo.project.exception.ResourceNotFoundException;
import com.aforo.project.model.User;
import com.aforo.project.repository.UserRepository;
import com.aforo.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        try {
            User user = mapToEntity(userDTO);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encode password
            User savedUser = userRepository.save(user);
            return mapToDTO(savedUser);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Data integrity violation: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating user: " + e.getMessage(), e);
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        try {
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encode updated password
            user.setEmail(userDTO.getEmail());
            User updatedUser = userRepository.save(user);
            return mapToDTO(updatedUser);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Data integrity violation: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating user: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            @SuppressWarnings("unused")
			User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Data integrity violation: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting user: " + e.getMessage(), e);
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream().map(this::mapToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving all users: " + e.getMessage(), e);
        }
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(),
                user.getCreated_at(), user.getUpdate_at(), null); // Map customers if necessary
    }

    private User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
