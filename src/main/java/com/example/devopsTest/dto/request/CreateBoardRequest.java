package com.example.devopsTest.dto.request;

import com.example.devopsTest.dto.entity.Board;

public record CreateBoardRequest(
        String userName, String content
) {
    public Board toEntity(){
        return Board.builder()
                .userName(userName)
                .content(content)
                .build();
    }
}
