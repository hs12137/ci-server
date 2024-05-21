package com.example.devopsTest.service;

import com.example.devopsTest.dto.request.CreateBoardRequest;
import com.example.devopsTest.dto.response.BoardDto;

import java.util.List;

public interface BoardService {
    void createBoard(CreateBoardRequest request);
    List<BoardDto> getAllBoards();
    void deleteBoard(long id);
}
