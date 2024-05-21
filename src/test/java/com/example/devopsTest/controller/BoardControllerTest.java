package com.example.devopsTest.controller;

import com.example.devopsTest.dto.entity.BoardRepository;
import com.example.devopsTest.dto.request.CreateBoardRequest;
import com.example.devopsTest.dto.response.BoardDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardControllerTest {
    @Autowired
    private BoardController boardController;
    @Autowired
    private BoardRepository boardRepository;

    @Nested
    @Transactional
    class 게시글_생성{

        @Test
        void 성공(){
            //given
            CreateBoardRequest request = new CreateBoardRequest("testUser", "testContent");
            int oldLen = boardRepository.findAll().size();
            //when
            boardController.createBoard(request);
            //then
            assertEquals(oldLen + 1, boardRepository.findAll().size());
        }
        @Test
        void 실패_이름_공백() {
            //given
            boardRepository.deleteAll();
            CreateBoardRequest request = new CreateBoardRequest("", "testContent");
            //when & then
            assertThrows(IllegalArgumentException.class, () -> boardController.createBoard(request));
        }
        @Test
        void 실패_내용_공백() {
            //given
            boardRepository.deleteAll();
            CreateBoardRequest request = new CreateBoardRequest("testUser", "");
            //when & then
            assertThrows(IllegalArgumentException.class, () -> boardController.createBoard(request));
        }
    }
    @Nested
    @Transactional
    class 게시글_목록_읽기{
        @Test
        void 성공(){
            //given
            boardRepository.deleteAll();
            CreateBoardRequest request1 = new CreateBoardRequest("testUser1", "testContent1");
            CreateBoardRequest request2 = new CreateBoardRequest("testUser2", "testContent2");
            CreateBoardRequest request3 = new CreateBoardRequest("testUser3", "testContent3");
            boardRepository.save(request1.toEntity());
            boardRepository.save(request2.toEntity());
            boardRepository.save(request3.toEntity());
            //when
            List<BoardDto> boards = boardController.getAllBoards();
            //then
            assertEquals(3, boards.size());
            System.out.println(boards);
        }
    }
    @Nested
    @Transactional
    class 게시글_삭제{
        @Test
        void 성공() {
            //given
            boardRepository.deleteAll();
            CreateBoardRequest request = new CreateBoardRequest("testUser", "testContent");
            boardRepository.save(request.toEntity());
            Long id = boardRepository.findAll().get(0).getId();
            //when
            boardController.deleteBoard(id);
            //then
            assertEquals(0, boardRepository.findAll().size());
        }
    }
}