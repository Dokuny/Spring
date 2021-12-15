package com.dokuny.comunitysite.member.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MemberForm {
    @NotBlank
    private String id;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;
    @NotNull
    private Integer age;
    @NotBlank
    private String gender;
    @NotBlank
    private String phone;
}
