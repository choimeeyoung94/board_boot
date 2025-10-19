package org.shark.appboard.service;

import org.shark.appboard.dto.BoardDTO;
import org.shark.appboard.dto.request.RequestBoardDTO;
import org.shark.appboard.dto.response.ResponseBoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    Page<BoardDTO> findBoardList(Pageable pageable);

    ResponseBoardDTO findById(Long bid);

    ResponseBoardDTO updateBoard(Long id, RequestBoardDTO requestBoardDTO);

    ResponseBoardDTO createBoard(RequestBoardDTO requestBoardDto);

    void deleteBoard(Long id);
}