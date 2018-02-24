package com.springsecurity.demo.config;

import com.springsecurity.demo.user.SysUserDetailsService;
import com.springsecurity.demo.user.User;
import com.springsecurity.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/23 10:52
 * @Modified By:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                User user = userRepository.findByUsername(s);
                if (user == null) {
                    throw new UsernameNotFoundException("用户名不存在！");
                } else {
                    System.out.println("s:" + s);
                    System.out.println("username:" + user.getUsername() + ";password:" + user.getPassword());
                    user.getAuthorities().stream().forEach(auth -> {
                        System.out.println(auth.getAuthority());
                    });
                }
                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
            }
        };
    }

    /*@Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new SysUserDetailsService();
    }*/

   /*@Bean
    @Override
    protected UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(org.springframework.security.core.userdetails.User.withUsername("root").password("root").authorities("USER").build());
        manager.createUser(org.springframework.security.core.userdetails.User.withUsername("admin").password("admin").authorities("USER").build());
        return manager;
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().anyRequest().and().authorizeRequests().antMatchers("/oauth/**").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
}
