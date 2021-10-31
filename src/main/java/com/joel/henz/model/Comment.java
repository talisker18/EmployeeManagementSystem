package com.joel.henz.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.joel.henz.dto.CommentForRegulationByUserDto;
import com.joel.henz.service.IDaoService;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String text;
	@Column(name="creation_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate creationDate;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "regulation_id")
	private Regulation regulation;
	private int userId;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int id, String text, LocalDate creationDate, Regulation regulation, int userId) {
		super();
		this.id = id;
		this.text = text;
		this.creationDate = creationDate;
		this.regulation = regulation;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Regulation getRegulation() {
		return regulation;
	}

	public void setRegulation(Regulation regulation) {
		this.regulation = regulation;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public static Comment createCommentWithDto(CommentForRegulationByUserDto commentDto,IDaoService<Regulation> regulationService) {
		Regulation regulation = regulationService.get(commentDto.getRegulationId());
		
		return new Comment(
				commentDto.getCommentId(),
				commentDto.getCommentText(),
				commentDto.getCommentCreationDate(),
				regulation,
				commentDto.getUserId()
		);
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + ", creationDate=" + creationDate + ", regulation=" + regulation
				+ ", userId=" + userId + "]";
	}
}
