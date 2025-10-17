package org.shark.appboard.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "boards")
@DynamicUpdate // update 쿼리를 만들때 변경된 내용만 업데이트 해달라
@EntityListeners(AuditingEntityListener.class)
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bid;
	
	@Column(nullable = false, length = 200)
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public Board() {}
	
	public static Board createBoard(String title, String content) {
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		return board;
	}
	
	@Override
	public String toString() {
		return "Board [bid=" + bid + ", title=" + title + ", content=" + content + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
}
