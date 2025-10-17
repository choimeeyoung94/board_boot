package org.shark.appboard.controller;

import java.util.List;
import java.util.Optional;

import org.shark.appboard.dto.BoardDTO;
import org.shark.appboard.entity.Board;
import org.shark.appboard.repository.BoardRepository;
import org.shark.appboard.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/list")
    public ResponseEntity<Page<BoardDTO>> getList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<BoardDTO> boards = boardService.getPage(pageable);
        return ResponseEntity.ok(boards);
    }
	
	@GetMapping("/detail")
	public ResponseEntity<Board> getBoard(@PathVariable Long bid) {
	    Board board = boardService.getBoard(bid);
		return ResponseEntity.ok(board);
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
		Board updated = boardService.updateBoard(id, boardDTO);
		return ResponseEntity.ok(updated);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Board> saveBoard(BoardDTO boardDTO) {
		Board saved = boardService.saveBoard(boardDTO);
		return ResponseEntity.ok(saved);
	}
	
	
	
}
