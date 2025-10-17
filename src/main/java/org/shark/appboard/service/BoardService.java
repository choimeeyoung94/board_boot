package org.shark.appboard.service;

import java.util.List;

import org.shark.appboard.dto.BoardDTO;
import org.shark.appboard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

	public List<Board> getList();

	Page<BoardDTO> getPage(Pageable pageable);
	
	public Board  getBoard(Long bid);

	public Board updateBoard(Long id, BoardDTO boardDTO);
	
	public Board saveBoard(BoardDTO baordDTO);
}
