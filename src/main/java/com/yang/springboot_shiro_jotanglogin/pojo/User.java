package com.yang.springboot_shiro_jotanglogin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String account;
    private String password;
    private String salt;
    private List<Role> roles;
}