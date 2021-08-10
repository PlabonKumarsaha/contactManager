package com.pks.contact_manager.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    private boolean enabled;
    private String imageUrl;
    @Column(length = 500)
    private String about;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy = "user")
    private List<Contact>contacts = new ArrayList<>();



}
