package com.test.assessment.dto;

import com.test.assessment.model.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Data
public class UserRequest implements Serializable {

    private Long id;

    private String title;

    private String status;

    private String firstname;

    private String email;

    private String mobile;

    private String password;

    private String lastname;

    private String verified;

    private Date dateregistered;

    private Date datedeactivated;

    private Date dateverified;

    private Set<String> roles;
}
