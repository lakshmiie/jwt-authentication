package com.example.authentication.service;

import com.example.authentication.entity.User;
import com.example.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public List<User> selectAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return  userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if (null != user) {
            return new org.springframework.security.core.userdetails
                    .User(user.getUserName(), user.getPassword(), new ArrayList());
        }else{
            log.error("Unable to find the user : {} ", user.getUserName());
           throw new UsernameNotFoundException("Unable to find the User : " + username);
        }

    }
}
