package com.dokuny.comunitysite.login.domain;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class MemberLoginDTO {

    @NotBlank
    private final String id;

    @NotBlank
    private final String pw;
}
