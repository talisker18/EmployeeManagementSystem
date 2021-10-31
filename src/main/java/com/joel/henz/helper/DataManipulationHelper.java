package com.joel.henz.helper;

import java.util.ArrayList;
import java.util.List;

import com.joel.henz.model.Comment;
import com.joel.henz.model.Employee;
import com.joel.henz.model.Regulation;
import com.joel.henz.service.CommentService;
import com.joel.henz.service.IDaoService;

import org.apache.log4j.Logger;

public final class DataManipulationHelper {
	
	private DataManipulationHelper() {
		//prevent instantiation
	}
	
	static Logger logger = Logger.getLogger(DataManipulationHelper.class);
	
	public static void checkIfStateOfRegulationHasToBeChanged(int regulationIdToCheck, IDaoService<Regulation> regulationService, CommentService commentService, IDaoService<Employee> employeeService) {
		//check if regulation state can be set to "COMPLETE"
		Regulation regulationToCheck = regulationService.get(regulationIdToCheck);
		
		//get all comments of this regulation
		List<Comment> listOfAllComments = commentService.getAll();
		List<Comment> listOfAllCommentsOfRegulation = new ArrayList<Comment>();
		
		for(Comment comm: listOfAllComments) {
			if(comm.getRegulation().getId() == regulationIdToCheck) {
				listOfAllCommentsOfRegulation.add(comm);
			}
		}
		
		//get all users of the regulations department
		int departmentId = regulationToCheck.getDepartment().getId();
		
		//get all users of this department
		List<Employee> listOfAllEmployees = employeeService.getAll();
		List<Employee> listOfEmployeesOfDepartment = new ArrayList<Employee>();
		for(Employee emp: listOfAllEmployees) {
			if(emp.getDepartment().getId() == departmentId) {
				listOfEmployeesOfDepartment.add(emp);
			}
		}
		
		boolean regulationComplete = true;
		
		for(Employee emp: listOfEmployeesOfDepartment) {
			
			boolean userAddedComment = false;
			
			for(Comment comm: listOfAllCommentsOfRegulation) {
				if(emp.getId() == comm.getUserId()) {
					userAddedComment = true;
					break;
				}
			}
			
			if(!userAddedComment) {
				regulationComplete = false;
				break;
			}
		}
		
		if(regulationComplete) {
			logger.info("Regulation with id "+regulationIdToCheck+" is completed. Set state to \"COMPLETED\"");
			regulationToCheck.setState("COMPLETED");
		}else {
			logger.info("Regulation with id "+regulationIdToCheck+" is not completed yet");
			regulationToCheck.setState("OPEN");
		}
		
		regulationService.update(regulationToCheck);
	}
}
