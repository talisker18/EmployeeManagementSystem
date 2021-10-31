package com.joel.henz.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.joel.henz.dto.EmployeeDepartmentDto;
import com.joel.henz.helper.DataManipulationHelper;
import com.joel.henz.model.Department;
import com.joel.henz.model.Employee;
import com.joel.henz.model.Regulation;
import com.joel.henz.service.CommentService;
import com.joel.henz.service.IDaoService;
import com.joel.henz.service.LoginAndSessionService;

@Controller
public class EmployeeController {
	
	@Autowired
	private IDaoService<Employee> employeeService;
	
	@Autowired
	private IDaoService<Department> departmentService;
	
	@Autowired
	private IDaoService<Regulation> regulationService;;
	
	@Autowired
	private LoginAndSessionService loginAndSessionService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CommentService commentService;
	
	private static Pattern regexFirstAndLastname = Pattern.compile("^[a-zA-Z]*$");
	private static Pattern regexEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	
	@RequestMapping(value = "/employeeDomain", method = RequestMethod.GET)
	public String enterEmployeeDomain() {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			return "employee-page";
		}else {
			return "redirect:/notLoggedIn";
		}
	}
	
	@RequestMapping(value = "/employeeDomain/newEmployee", method = RequestMethod.GET)
	public ModelAndView newEmployee(@ModelAttribute EmployeeDepartmentDto employeeDepartmentDto, ModelAndView model) { 
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			//check first if user has admin role
			if(session.getAttribute("role").equals("admin")) {
				List<Department> departmentList = departmentService.getAll();
				
				model.addObject("departmentList", departmentList);
				model.setViewName("EmployeeForm"); //go to EmployeeForm.jsp. The input fields must match the name of the instance variables of bean class Employee, e.g. firstName, lastName etc
				return model;
			}else {
				//model.setViewName("redirect:/employeeDomain/actionNotAllowed");
				model.setViewName("redirect:/actionNotAllowed");
				return model;
			}
		}else {
			model.setViewName("redirect:/notLoggedIn");
			return model;
		}

	}
	
	@RequestMapping(value = "/employeeDomain/newEmployee/saveEmployee", method = RequestMethod.POST)
	public RedirectView saveEmployee(@ModelAttribute EmployeeDepartmentDto employeeDepartmentDto, RedirectAttributes redirectAttributes) {
		Employee employee = employeeDepartmentDto.createEmployee(departmentService);
		
		//java methods are "passing by value". The object with reference variable "redirectAttributes" is changed in validateUserInputForEmployee() method. These changes are also applied when method execution has finished
		if(validateUserInputForEmployee(employeeDepartmentDto, redirectAttributes)) {
			//due to new employee for a department, we have to set all regulations of that department to state = OPEN or in case of update, we have to update state as well if department of employee has changed
			List<Regulation> listOfAllRegulations = this.regulationService.getAll();
			List<Regulation> listOfAllRegulationsOfDepartment = new ArrayList<Regulation>();
			
			for(Regulation reg: listOfAllRegulations) {
				if(reg.getDepartment().getId() == employeeDepartmentDto.getDepartmentId()) {
					listOfAllRegulationsOfDepartment.add(reg);
				}
			}
			
			if (employeeDepartmentDto.getEmployeeId() == 0) { // if employee id is 0 then creating the
				// employee other updating the employee
				
				employeeService.add(employee);
				redirectAttributes.addFlashAttribute("successMessage", "successfully added new employee");
				
				for(Regulation reg: listOfAllRegulationsOfDepartment) {
					reg.setState("OPEN");
					this.regulationService.update(reg);
				}
				
			} else {
				employeeService.update(employee);
				
				//check if state of regulations has to be changed because department of employee could have been changed
				for(Regulation reg: listOfAllRegulationsOfDepartment) {
					DataManipulationHelper.checkIfStateOfRegulationHasToBeChanged(reg.getId(), regulationService, commentService, employeeService);
				}
				
				redirectAttributes.addFlashAttribute("successMessage", "successfully updated employee with id"+employee.getId());
			}
			
			return new RedirectView("/EmployeeManagementSystem/operationSuccessfull");
			
		}else {
			redirectAttributes.addFlashAttribute("employeeDepartmentDto", employeeDepartmentDto);
			return new RedirectView("/EmployeeManagementSystem/employeeDomain/newEmployee"); //use redirect beginning with / to set completely new path from beginning
		}
	}
	
	@RequestMapping(value = "/employeeDomain/chooseEmployee", method = RequestMethod.POST)
	public ModelAndView chooseEmployeeToDelete(ModelAndView model) {
		List<Employee> listEmployee = employeeService.getAll();
		model.addObject("listEmployee", listEmployee);
		model.setViewName("delete-or-update-employee");
		return model;
	}
	
	@RequestMapping(value = "/employeeDomain/deleteEmployee", method = RequestMethod.GET)
	public RedirectView deleteEmployee(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			if(session.getAttribute("role").equals("admin")) {
				int employeeId = Integer.parseInt(request.getParameter("id"));
				employeeService.delete(employeeId);
				redirectAttributes.addFlashAttribute("successMessage", "successfully deleted employee with id "+employeeId);
				return new RedirectView("/EmployeeManagementSystem/operationSuccessfull");
			}else {
				return new RedirectView("/EmployeeManagementSystem/actionNotAllowed");
			}
		}else {
			return new RedirectView("/EmployeeManagementSystem/notLoggedIn");
		}
	}
	
	@RequestMapping(value = "/employeeDomain/listAllEmployees", method = RequestMethod.POST)
	public ModelAndView listEmployee(ModelAndView model) throws IOException {
		List<Employee> listEmployee = employeeService.getAll();
		model.addObject("listEmployee", listEmployee);
		model.setViewName("employees-overview");
		return model;
	}
	
	@RequestMapping(value = "/employeeDomain/editEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			if(session.getAttribute("role").equals("admin")) {
				int employeeId = Integer.parseInt(request.getParameter("id"));
				Employee employee = employeeService.get(employeeId);
				EmployeeDepartmentDto empDepDto;
				
				//check if department is null
				if(employee.getDepartment() == null) {
					empDepDto = new EmployeeDepartmentDto(
							employeeId, 
							employee.getFirstName(), 
							employee.getLastName(), 
							employee.getDateOfBirth(), 
							employee.getEmail(), 
							employee.getRole(), 
							employee.getPassword(), 
							employee.getUserName(), 
							-1
					);
				}else {
					empDepDto = new EmployeeDepartmentDto(
							employeeId, 
							employee.getFirstName(), 
							employee.getLastName(), 
							employee.getDateOfBirth(), 
							employee.getEmail(), 
							employee.getRole(), 
							employee.getPassword(), 
							employee.getUserName(), 
							employee.getDepartment().getId()
					);
				}
				
				ModelAndView model = new ModelAndView("EmployeeForm");
				model.addObject("employeeDepartmentDto", empDepDto);
				
				List<Department> departmentList = departmentService.getAll();
				
				model.addObject("departmentList", departmentList);

				return model;
			}else {
				return new ModelAndView("redirect:/actionNotAllowed");
			}
		}else {
			return new ModelAndView("redirect:/notLoggedIn");
		}
	}
	
	@RequestMapping(value = "/employeeDomain/redirectToDomainPageEmployee", method = RequestMethod.GET)
	public String redirectToDomainPageEmployee() {
		return "redirect:/employeeDomain";
	}
	
	private boolean validateUserInputForEmployee(EmployeeDepartmentDto emp, RedirectAttributes redirectAttributes) {
		boolean validInputs = true;
		
		//check if first and lastname are in correct format
		Matcher m = regexFirstAndLastname.matcher(emp.getFirstName());   
		
		if(m.matches() && !emp.getFirstName().isEmpty()) {
			redirectAttributes.addFlashAttribute("inputFirstname", "<p style=\"color:green;\">Input OK</p>");
		}else {
			validInputs = false;
			redirectAttributes.addFlashAttribute("inputFirstname", "<p style=\"color:red;\">First Name must contain only letters</p>");
		}
		
		//lastname
		m = regexFirstAndLastname.matcher(emp.getLastName());
		
		if(m.matches() && !emp.getLastName().isEmpty()) {
			redirectAttributes.addFlashAttribute("inputLastname", "<p style=\"color:green;\" >Input OK</p>");
		}else {
			validInputs = false;
			redirectAttributes.addFlashAttribute("inputLastname", "<p style=\"color:red;\">Last Name must contain only letters</p>");
		}
		
		//username shall not be empty
		if(!emp.getUserName().isEmpty()) {
			redirectAttributes.addFlashAttribute("inputUsername", "<p style=\"color:green;\">Input OK</p>");
		}else {
			validInputs = false;
			redirectAttributes.addFlashAttribute("inputUsername", "<p style=\"color:red;\">Username may not be empty</p>");
		}
		
		//validate email address format
		m = regexEmail.matcher(emp.getEmail());
		
		if(m.matches() && !emp.getEmail().isEmpty()) {
			redirectAttributes.addFlashAttribute("inputEmail", "<p style=\"color:green;\">Input OK</p>");
		}else {
			validInputs = false;
			redirectAttributes.addFlashAttribute("inputEmail", "<p style=\"color:red;\">Format for email is not correct</p>");
		}
		
		//validate date of birth. Employees shall not be younger than 24 years
		LocalDate todayMinus24Years = LocalDate.now().minusYears(24);
		
		if(emp.getDateOfBirth() == null) {
			validInputs = false;
			redirectAttributes.addFlashAttribute("inputDob", "<p style=\"color:red;\">Employee date of birth must be filled in</p>");
		}else {
			if(emp.getDateOfBirth().isBefore(todayMinus24Years)) {
				redirectAttributes.addFlashAttribute("inputDob", "<p style=\"color:green;\">Input OK</p>");
			}else {
				validInputs = false;
				redirectAttributes.addFlashAttribute("inputDob", "<p style=\"color:red;\">Employee must be older than 24 years</p>");
			}
		}
		
		return validInputs;
	}
	
}
