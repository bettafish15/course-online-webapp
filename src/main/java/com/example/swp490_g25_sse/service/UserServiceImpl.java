package com.example.swp490_g25_sse.service;

import com.example.swp490_g25_sse.dto.UserRegistrationDto;
import com.example.swp490_g25_sse.model.Role;
import com.example.swp490_g25_sse.model.User;
import com.example.swp490_g25_sse.repository.RoleRepository;
import com.example.swp490_g25_sse.repository.UserRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bettafish15
 */
@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        Role existedRole = roleRepository.findFirstByName((registrationDto.getRole()));
        Role role;

        if (existedRole != null) {
            role = existedRole;
        } else {
            role = new Role(registrationDto.getRole());
        }

        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(role));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.trace("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.trace(username);
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
