package org.shark.appboard.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.shark.appboard.dto.BoardDTO;
import org.shark.appboard.dto.request.RequestBoardDTO;
import org.shark.appboard.dto.response.PageResponseDTO;
import org.shark.appboard.dto.response.ResponseBoardDTO;
import org.shark.appboard.entity.Board;
import org.shark.appboard.repository.BoardRepository;
import org.shark.appboard.service.BoardService;
import org.springframework.http.MediaType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/board")
@RequiredArgsConstructor
@RestController
public class BoardController {
	
	private final BoardService boardService;
	
	   @GetMapping("/list")
	   public ResponseEntity<PageResponseDTO<BoardDTO>> findAll(
	            @PageableDefault(page = 1, size = 5, sort = "bid", direction = Direction.DESC) Pageable pageable
	   ) {
	        // 외부에서 1-based로 받으면 0-based로 보정
	        pageable = pageable.withPage(pageable.getPageNumber() - 1);
	
	        Page<BoardDTO> page = boardService.findBoardList(pageable);
	        PageResponseDTO<BoardDTO> response = PageResponseDTO.from(page);
	        return ResponseEntity.ok(response);
	  }

	
	@GetMapping("/detail/{bid}")
	public ResponseEntity<ResponseBoardDTO> findById(@PathVariable("bid") Long bid) {
	    ResponseBoardDTO apiResponseDTO = boardService.findById(bid);
		return ResponseEntity.ok(apiResponseDTO);
	}
	
	@PostMapping(value = "/update/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBoardDTO> updateBoard(@PathVariable("id") Long id, @RequestBody RequestBoardDTO boardDTO) {
		ResponseBoardDTO apiResponseDTO = boardService.updateBoard(id, boardDTO);
		return ResponseEntity.ok(apiResponseDTO);
	}
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBoardDTO> createBoard(@RequestBody RequestBoardDTO requestBoardDto) {
		ResponseBoardDTO apiResponseDTO = boardService.createBoard(requestBoardDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseDTO);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteBoard(@PathVariable("id") Long id) {
		    boardService.deleteBoard(id); 
		    return ResponseEntity.noContent().build();
		
	}
	
}
