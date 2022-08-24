package com.example.springboot.entity.DTO;

import lombok.Data;

@Data
public class UserQueryDTO {
    private  Integer currentPage;
    private Integer pageSize;
    private String username;
    private String email;
}
