package com.Board.controller;

import com.Board.dto.BoardDTO;
import com.Board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("member/board/write")
    public String writeForm() {
        return "write";
    }

    @PostMapping("member/board/write")
    public String write(@ModelAttribute BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }

    @GetMapping("member/board/boardlist")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 boardlist.html에 보여준다.
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "boardlist";
    }

    @GetMapping("member/board/boardlist/{id}")
    public String findById(@PathVariable Long id, Model model) {
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 boardDetail.html에 출력
        */
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "boardDetail";
    }
}
