package com.example.devopsTest.controller;

import com.example.devopsTest.dto.request.CreateBoardRequest;
import com.example.devopsTest.dto.response.BoardDto;
import com.example.devopsTest.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@CrossOrigin(origins = "*")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public void createBoard(@RequestBody CreateBoardRequest request) {
        boardService.createBoard(request);
    }
    @GetMapping
    public List<BoardDto> getAllBoards() {
        return boardService.getAllBoards();
    }
    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}
