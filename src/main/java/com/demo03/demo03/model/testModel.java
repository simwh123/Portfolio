package com.demo03.demo03.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USERTEST")
public class testModel {
    @Id
    @Column(name = "USER_ID")
    private String id;
    @Column(name = "USER_PW")
    private String pw;
    @Column(name = "USER_EMAIL")
    private String email;
    @Column(name = "USER_NAME")
    private String name;
    @Column(name = "USER_BIRTH")
    private String birth;
    @Column(name = "USER_PHONE")
    private String phone;
}
