package com.aya.service.impl;

import com.aya.common.UserPrincipal;
import com.aya.entity.User;
import com.aya.repository.UserRepository;
import com.aya.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByUserName(username);
        if (user==null){
            throw new UsernameNotFoundException("This user does not exists");
        }

        return new UserPrincipal(user);
    }
}
