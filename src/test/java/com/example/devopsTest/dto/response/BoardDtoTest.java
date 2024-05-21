package com.example.devopsTest.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BoardDtoTest {

    @Test
    void builder() {
        String dto = BoardDto.builder()
                .userName("test")
                .toString();
    }
}