package com.joel.henz.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.joel.henz.model.Department;
import com.joel.henz.model.Regulation;

public class CommentForRegulationByUserDto {
	private int regulationId;
	private String regulationType;
	private String regulationState;
	private String regulationDetails;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate regulationCreationDate;
	private Department department;
	private int commentId;
	private String commentText;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate commentCreationDate;
	private int userId;
	
	public CommentForRegulationByUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentForRegulationByUserDto(int regulationId, String regulationType, String regulationState,
			String regulationDetails, LocalDate regulationCreationDate, Department department, int commentId,
			String commentText, LocalDate commentCreationDate, int userId) {
		super();
		this.regulationId = regulationId;
		this.regulationType = regulationType;
		this.regulationState = regulationState;
		this.regulationDetails = regulationDetails;
		this.regulationCreationDate = regulationCreationDate;
		this.department = department;
		this.commentId = commentId;
		this.commentText = commentText;
		this.commentCreationDate = commentCreationDate;
		this.userId = userId;
	}

	public int getRegulationId() {
		return regulationId;
	}

	public void setRegulationId(int regulationId) {
		this.regulationId = regulationId;
	}

	public String getRegulationType() {
		return regulationType;
	}

	public void setRegulationType(String regulationType) {
		this.regulationType = regulationType;
	}

	public String getRegulationState() {
		return regulationState;
	}

	public void setRegulationState(String regulationState) {
		this.regulationState = regulationState;
	}

	public String getRegulationDetails() {
		return regulationDetails;
	}

	public void setRegulationDetails(String regulationDetails) {
		this.regulationDetails = regulationDetails;
	}

	public LocalDate getRegulationCreationDate() {
		return regulationCreationDate;
	}

	public void setRegulationCreationDate(LocalDate regulationCreationDate) {
		this.regulationCreationDate = regulationCreationDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public LocalDate getCommentCreationDate() {
		return commentCreationDate;
	}

	public void setCommentCreationDate(LocalDate commentCreationDate) {
		this.commentCreationDate = commentCreationDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public static CommentForRegulationByUserDto createCommentDtoWithRegulation(int userId, Regulation regulation) {
		CommentForRegulationByUserDto commentDto = new CommentForRegulationByUserDto();
		commentDto.setRegulationId(regulation.getId());
		commentDto.setRegulationType(regulation.getType());
		commentDto.setRegulationState(regulation.getState());
		commentDto.setRegulationDetails(regulation.getDetails());
		commentDto.setRegulationCreationDate(regulation.getCreationDate());
		
		commentDto.setUserId(userId);
		
		return commentDto;
	}

	@Override
	public String toString() {
		return "CommentForRegulationByUserDto [regulationId=" + regulationId + ", regulationType=" + regulationType
				+ ", regulationState=" + regulationState + ", regulationDetails=" + regulationDetails
				+ ", regulationCreationDate=" + regulationCreationDate + ", department=" + department + ", commentId="
				+ commentId + ", commentText=" + commentText + ", commentCreationDate=" + commentCreationDate
				+ ", userId=" + userId + "]";
	}
}
