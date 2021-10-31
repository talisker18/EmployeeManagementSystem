package com.joel.henz.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.joel.henz.model.Department;
import com.joel.henz.model.Regulation;
import com.joel.henz.service.IDaoService;

public class RegulationDepartmentDto {
	
	private int regulationId;
	private String type;
	private String details;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate creationDate;
	private int departmentId;
	
	public RegulationDepartmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegulationDepartmentDto(int regulationId, String type, String details, LocalDate creationDate,
			int departmentId) {
		super();
		this.regulationId = regulationId;
		this.type = type;
		this.details = details;
		this.creationDate = creationDate;
		this.departmentId = departmentId;
	}

	public int getRegulationId() {
		return regulationId;
	}

	public void setRegulationId(int regulationId) {
		this.regulationId = regulationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "RegulationDepartmentDto [regulationId=" + regulationId + ", type=" + type
				+ ", details=" + details + ", creationDate=" + creationDate + ", departmentId=" + departmentId + "]";
	}
	
	public Regulation createRegulation(IDaoService<Department> departmentService) {
		Department dep = departmentService.get(this.departmentId);
		Regulation reg = new Regulation(
				this.regulationId,
				this.type,
				"OPEN",
				this.details,
				this.creationDate,
				dep
		);
		
		return reg;
	}
}
