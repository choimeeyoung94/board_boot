package org.shark.appboard.dto;

import java.time.LocalDateTime;

import org.shark.appboard.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BoardDTO {
	private Long bid;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Board toEntity() {
		return Board.createBoard(title, content);
	}
	
	public static BoardDTO toDTO(Board board) {
		return BoardDTO.builder()
				.title(board.getTitle())
				.content(board.getContent())
				.createdAt(board.getCreatedAt())
				.updatedAt(board.getUpdatedAt())
				.build();
	}
	
}
