package com.example.devopsTest.service;

import com.example.devopsTest.dto.entity.Board;
import com.example.devopsTest.dto.entity.BoardRepository;
import com.example.devopsTest.dto.request.CreateBoardRequest;
import com.example.devopsTest.dto.response.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public void createBoard(CreateBoardRequest request) {
        if (!validRequest(request)) throw new IllegalArgumentException("UserName and Content cannot be empty");
        Board board = request.toEntity();
        boardRepository.save(board);
    }

    @Override
    public List<BoardDto> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(BoardDto::toDto).toList();
    }

    @Override
    public void deleteBoard(long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Board not found"));
        boardRepository.delete(board);
    }

    private boolean validRequest(CreateBoardRequest request) {
        if(request.userName().replace(" ", "").isEmpty() ||
            request.content().replace(" ", "").isEmpty()) return false;
        return true;
    }
}
