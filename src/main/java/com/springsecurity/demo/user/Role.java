package com.springsecurity.demo.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/22 10:29
 * @Modified By:
 */
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue
    protected Integer id;

    private String roleName;
}
