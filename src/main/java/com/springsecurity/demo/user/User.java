package com.springsecurity.demo.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/22 10:20
 * @Modified By:
 */
@Data
@Entity
public class User implements UserDetails{

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    private String email;

    private Date lastPassWordResetDate;

    /**
     * CascadeType.REFRESH:级联刷新
     * fetch = FetchType.EAGER:关系类在主类加载的时候同时加载
     */
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths=new ArrayList<>();
        List<Role> roles=this.roles;
        for (Role role:roles){
            auths.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return auths;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
