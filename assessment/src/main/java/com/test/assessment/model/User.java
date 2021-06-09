package com.test.assessment.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name="userinformation")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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

    @ManyToMany @Fetch(FetchMode.JOIN)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();





}
