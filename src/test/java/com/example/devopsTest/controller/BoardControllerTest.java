package com.example.devopsTest.controller;

import com.example.devopsTest.dto.entity.BoardRepository;
import com.example.devopsTest.dto.request.CreateBoardRequest;
import com.example.devopsTest.dto.response.BoardDto;
import com.example.devopsTest.service.BoardService;
import com.example.devopsTest.service.BoardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BoardController.class)
class BoardControllerTest {
    @MockBean
    private BoardServiceImpl boardService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 전체_게시글_가져오기() throws Exception {
        //given
        BDDMockito.given(boardService.getAllBoards())
                .willReturn(List.of(
                        new BoardDto(1L, "testName1", "testContent1"),
                        new BoardDto(2L, "testName2", "testContent2"),
                        new BoardDto(3L, "testName3", "testContent3")
                ));
        //when & then
        mockMvc.perform(get("/api/boards"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 게시글_생성_성공() throws Exception {
        //given
        CreateBoardRequest request = new CreateBoardRequest("testName4", "testContent4");
        BDDMockito.willDoNothing().given(boardService).createBoard(request);
        //when & then
        mockMvc.perform(
                post("/api/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void 게시글_생성_실패_아이디_공백() throws Exception {
        //given
        CreateBoardRequest request = new CreateBoardRequest("  ", "testContent4");
        BDDMockito.willThrow(new IllegalArgumentException()).given(boardService).createBoard(request);
        //when & then
        mockMvc.perform(
                        post("/api/boards")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
    void 게시글_생성_실패_내용_공백() throws Exception {
        //given
        CreateBoardRequest request = new CreateBoardRequest("testName4", "   ");
        BDDMockito.willThrow(new IllegalArgumentException()).given(boardService).createBoard(request);
        //when & then
        mockMvc.perform(
                        post("/api/boards")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
    void 게시글_삭제_성공() throws Exception {
        //given
        BDDMockito.willDoNothing().given(boardService).deleteBoard(1L);
        //when & then
        mockMvc.perform(
                        delete("/api/boards/1")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }





//    @Autowired
//    private BoardController boardController;
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Nested
//    @Transactional
//    class 게시글_생성{
//
//        @Test
//        void 성공(){
//            //given
//            CreateBoardRequest request = new CreateBoardRequest("testUser", "testContent");
//            int oldLen = boardRepository.findAll().size();
//            //when
//            boardController.createBoard(request);
//            //then
//            assertEquals(oldLen + 1, boardRepository.findAll().size());
//        }
//        @Test
//        void 실패_이름_공백() {
//            //given
//            boardRepository.deleteAll();
//            CreateBoardRequest request = new CreateBoardRequest("", "testContent");
//            //when & then
//            assertThrows(IllegalArgumentException.class, () -> boardController.createBoard(request));
//        }
//        @Test
//        void 실패_내용_공백() {
//            //given
//            boardRepository.deleteAll();
//            CreateBoardRequest request = new CreateBoardRequest("testUser", "");
//            //when & then
//            assertThrows(IllegalArgumentException.class, () -> boardController.createBoard(request));
//        }
//    }
//    @Nested
//    @Transactional
//    class 게시글_목록_읽기{
//        @Test
//        void 성공(){
//            //given
//            boardRepository.deleteAll();
//            CreateBoardRequest request1 = new CreateBoardRequest("testUser1", "testContent1");
//            CreateBoardRequest request2 = new CreateBoardRequest("testUser2", "testContent2");
//            CreateBoardRequest request3 = new CreateBoardRequest("testUser3", "testContent3");
//            boardRepository.save(request1.toEntity());
//            boardRepository.save(request2.toEntity());
//            boardRepository.save(request3.toEntity());
//            //when
//            List<BoardDto> boards = boardController.getAllBoards();
//            //then
//            assertEquals(3, boards.size());
//            System.out.println(boards);
//        }
//    }
//    @Nested
//    @Transactional
//    class 게시글_삭제{
//        @Test
//        void 성공() {
//            //given
//            boardRepository.deleteAll();
//            CreateBoardRequest request = new CreateBoardRequest("testUser", "testContent");
//            boardRepository.save(request.toEntity());
//            Long id = boardRepository.findAll().get(0).getId();
//            //when
//            boardController.deleteBoard(id);
//            //then
//            assertEquals(0, boardRepository.findAll().size());
//        }
//    }
}