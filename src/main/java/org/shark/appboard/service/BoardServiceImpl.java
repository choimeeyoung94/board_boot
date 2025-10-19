package org.shark.appboard.service;

import org.shark.appboard.dto.BoardDTO;
import org.shark.appboard.dto.request.RequestBoardDTO;
import org.shark.appboard.dto.response.ResponseBoardDTO;
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
    public Page<BoardDTO> findBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable)
                .map(BoardDTO::toDTO);
    }

    @Override
    public ResponseBoardDTO findById(Long bid) {
        Board board = boardRepository.findById(bid)
                .orElseThrow(() -> new EntityNotFoundException("Board not found: " + bid));
        return board.toDTO();
    }

    @Transactional
    @Override
    public ResponseBoardDTO updateBoard(Long id, RequestBoardDTO requestBoardDTO) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found: " + id));
        board.setTitle(requestBoardDTO.getTitle());
        board.setContent(requestBoardDTO.getContent());
        Board saved = boardRepository.save(board);
        return saved.toDTO();
    }

    @Transactional
    @Override
    public ResponseBoardDTO createBoard(RequestBoardDTO requestBoardDTO) {
        Board board = requestBoardDTO.toEntity();
        Board saved = boardRepository.save(board);
        return saved.toDTO();
    }

    @Transactional
    @Override
    public void deleteBoard(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new EntityNotFoundException("Board not found: " + id);
        }
        boardRepository.deleteById(id);
    }
}