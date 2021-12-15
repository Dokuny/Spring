package com.dokuny.comunitysite.board.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class Reply {
    private final int id;
    private final String user;
    private final String replyText;
}
