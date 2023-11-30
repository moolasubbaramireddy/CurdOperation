package com.curdOperation.service;

import com.curdOperation.entity.User;
import com.curdOperation.exception.ResourceNotFoundException;
import com.curdOperation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
      return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id:"+id));
        return user;
    }

    public User updateUser(User user, Long id) {
        User user1 = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user with is not avaiable" + id));
       if (user1 != null){
           user1.setName(user.getName());
           user1.setUsername(user.getUsername());
           user1.setEmail(user.getEmail());
       return userRepository.save(user1);
       }else {
           return  null;
       }
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User is not found with id :  " + id));
        userRepository.delete(user);
    }
}
