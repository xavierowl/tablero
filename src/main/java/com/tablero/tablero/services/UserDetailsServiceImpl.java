package com.tablero.tablero.services;

import com.tablero.tablero.domains.SecurityUser;
import com.tablero.tablero.domains.User;
import com.tablero.tablero.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Security;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null){
            throw new UsernameNotFoundException("No se ha encontrado el usuario.");
        }
        return new SecurityUser(user);
    }
}
