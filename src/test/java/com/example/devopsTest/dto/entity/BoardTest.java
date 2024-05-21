package com.example.devopsTest.dto.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void builder() {
        Board board = Board.builder().build();
        board.setContent("test");
        assertEquals("test", board.getContent());

        Board.builder().id(1L);
        Board.builder().toString();
        board.getId();
    }
}