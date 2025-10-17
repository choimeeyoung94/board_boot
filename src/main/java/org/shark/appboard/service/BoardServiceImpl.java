package org.shark.appboard.service;

import java.util.List;
import java.util.Optional;

import org.shark.appboard.dto.BoardDTO;
import org.shark.appboard.entity.Board;
import org.shark.appboard.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;

	@Override
	public List<Board> getList() {
		// TODO Auto-generated method stub
		return boardRepository.findAll();
	}

	@Override
	public Board getBoard(Long bid) {
		// TODO Auto-generated method stub
		return boardRepository.findById(bid)
                .orElseThrow(() -> new EntityNotFoundException("Board not found: " + bid));
	}

	@Transactional
	@Override
	public Board updateBoard(Long id, BoardDTO boardDTO) {
		Board board = boardRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException("Board not found: " + id));
		board.setTitle(boardDTO.getTitle());
		board.setContent(boardDTO.getContent());
		return boardRepository.save(board);
	}

	@Transactional
	@Override
	public Board saveBoard(BoardDTO boardDTO) {
		Board board = boardDTO.toEntity();		
		return boardRepository.save(board);
	}

	@Override
	public Page<BoardDTO> getPage(Pageable pageable) {
		 return boardRepository.findAll(pageable).map(BoardDTO::toDTO);
	}
	
	
	

}
