package com.dokuny.comunitysite.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class PostDTO {
    private Long id;
    private final String title;
    private final String user;
    private final Date date;
    private final Long views;
    private final String text;

}
