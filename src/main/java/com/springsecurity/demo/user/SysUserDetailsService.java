package com.springsecurity.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/24 9:57
 * @Modified By:
 */
public class SysUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user;
        user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        } else {
            System.out.println("s:" + s);
            System.out.println("username:" + user.getUsername() + ";password:" + user.getPassword());
            user.getAuthorities().stream().forEach(auth->{
                System.out.println(auth.getAuthority());
            });
            //System.out.println(user.getAuthorities().stream().);
            //return user;
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getAuthorities());
        }
    }
}
