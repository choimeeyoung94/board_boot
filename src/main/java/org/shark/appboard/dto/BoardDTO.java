package org.shark.appboard.dto;

import java.time.LocalDateTime;

import org.shark.appboard.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardDTO {

    private Long bid;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardDTO toDTO(Board board) {
        BoardDTO dto = new BoardDTO();
        dto.setBid(board.getBid());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setCreatedAt(board.getCreatedAt());
        dto.setUpdatedAt(board.getUpdatedAt());
        return dto;
    }
}