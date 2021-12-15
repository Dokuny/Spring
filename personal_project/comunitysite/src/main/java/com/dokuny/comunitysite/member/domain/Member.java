package com.dokuny.comunitysite.member.domain;

import lombok.*;

@Data
@Getter @Setter
public class Member {

    public Member() {

    }

    public Member(String id, String pw, String name, Integer age, String gender, String phone) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
    }

    private String id;
    private String pw;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
}
