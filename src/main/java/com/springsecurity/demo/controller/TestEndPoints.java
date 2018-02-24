package com.springsecurity.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/23 9:27
 * @Modified By:
 */
@RestController
public class TestEndPoints {

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id:" + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable Integer id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return "order id:"+id;
    }
}
