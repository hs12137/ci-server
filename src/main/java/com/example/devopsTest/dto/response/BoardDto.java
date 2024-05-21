package com.example.devopsTest.dto.response;

import com.example.devopsTest.dto.entity.Board;
import lombok.Builder;

@Builder
public record BoardDto(
        Long id, String userName, String content
) {
    public static BoardDto toDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .userName(board.getUserName())
                .content(board.getContent())
                .build();
    }
}
