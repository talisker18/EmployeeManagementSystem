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


@Entity
@Table(name = "regulation")
public class Regulation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String type;
	@Column
	private String state;
	@Column
	private String details;
	@Column(name="creation_date")
	private LocalDate creationDate;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
	private Department department;

	public Regulation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Regulation(int id, String type, String state, String details, LocalDate creationDate,
			Department department) {
		super();
		this.id = id;
		this.type = type;
		this.state = state;
		this.details = details;
		this.creationDate = creationDate;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Regulation [id=" + id + ", type=" + type + ", state=" + state + ", details=" + details
				+ ", creationDate=" + creationDate + ", department=" + department + "]";
	}
}

