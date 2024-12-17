package main.services;


import main.CustomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final UserService userService;

    public UserDetailsServiceImpl (UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        CustomUser user = userService.findByLogin(login);

       if(user == null){
           throw new UsernameNotFoundException(login + " not found");
       }

        List<GrantedAuthority> roles = List.of();

        return new User (user.getLogin(), user.getPassword(), roles);
    }
}
