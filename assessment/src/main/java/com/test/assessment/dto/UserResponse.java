package com.test.assessment.dto;

import com.test.assessment.model.Role;
import lombok.Data;

import java.io.Serializable;
@Data
public class UserResponse implements Serializable {

    private String title;

    private String status;

    private String firstname;

    private String email;

    private String mobile;

    private String password;

    private String lastname;

    private String verified;

    private String dateregistered;

    private String datedeactivated;

    private String dateverified;

    private Role role;
}
