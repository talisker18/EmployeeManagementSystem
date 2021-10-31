package com.joel.henz.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.joel.henz.model.Department;
import com.joel.henz.service.IDaoService;
import com.joel.henz.service.LoginAndSessionService;

@Controller
public class DepartmentController {
	
	@Autowired
	private LoginAndSessionService loginAndSessionService;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IDaoService<Department> departmentService;
	
	@RequestMapping(value = "/departmentDomain", method = RequestMethod.GET)
	public String enterDepartmentDomain() {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			return "department-page";
		}else {
			return "redirect:/notLoggedIn";
		}
	}
	
	@RequestMapping(value = "/departmentDomain/newDepartment", method = RequestMethod.GET)
	public ModelAndView newDepartment(@ModelAttribute Department department, ModelAndView model) { 
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			//check first if user has admin role
			if(session.getAttribute("role").equals("admin")) {
				model.setViewName("DepartmentForm");
				return model;
			}else {
				model.setViewName("redirect:/actionNotAllowed");
				return model;
			}
		}else {
			model.setViewName("redirect:/notLoggedIn");
			return model;
		}
	}
	
	@RequestMapping(value = "/departmentDomain/newDepartment/saveDepartment", method = RequestMethod.POST)
	public RedirectView saveDepartment(@ModelAttribute Department department, RedirectAttributes redirectAttributes) {
		
		if(validateUserInputForDepartment(department, redirectAttributes)) {
			if (department.getId() == 0) {
				
				departmentService.add(department);
				redirectAttributes.addFlashAttribute("successMessage", "successfully added new department");
			} else {
				departmentService.update(department);
				redirectAttributes.addFlashAttribute("successMessage", "successfully updated department with id "+department.getId());
			}
			
			return new RedirectView("/EmployeeManagementSystem/operationSuccessfull");
			
		}else {
			redirectAttributes.addFlashAttribute("department", department);
			return new RedirectView("/EmployeeManagementSystem/departmentDomain/newDepartment"); //use redirect beginning with / to set completely new path from beginning
		}
	}
	
	/**
	 * the following blocks could be used to edit / delete department. But its not in the business requirements and therefore not implemented
	 * 
	 * 
	 * @RequestMapping(value = "/departmentDomain/chooseDepartment", method = RequestMethod.POST)
	public ModelAndView chooseDepartmentToUpdateOrDelete(ModelAndView model) {
		List<Department> listDepartment = departmentService.getAll();
		model.addObject("listDepartment", listDepartment);
		model.setViewName("delete-or-update-department");
		return model;
	}
	
	@RequestMapping(value = "/departmentDomain/deleteDepartment", method = RequestMethod.GET)
	public RedirectView deleteDepartment(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			if(session.getAttribute("role").equals("admin")) {
				int departmentId = Integer.parseInt(request.getParameter("id"));
				departmentService.delete(departmentId);
				redirectAttributes.addFlashAttribute("successMessage", "successfully deleted department with id "+departmentId);
				return new RedirectView("/EmployeeManagementSystem/operationSuccessfull");
			}else {
				return new RedirectView("/EmployeeManagementSystem/actionNotAllowed");
			}
		}else {
			return new RedirectView("/EmployeeManagementSystem/notLoggedIn");
		}
	}
	
	@RequestMapping(value = "/departmentDomain/editDepartment", method = RequestMethod.GET)
	public ModelAndView editDepartment(HttpServletRequest request) {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			if(session.getAttribute("role").equals("admin")) {
				int departmentId = Integer.parseInt(request.getParameter("id"));
				Department department = departmentService.get(departmentId);
				
				ModelAndView model = new ModelAndView("DepartmentForm");
				model.addObject("department", department);

				return model;
			}else {
				return new ModelAndView("redirect:/actionNotAllowed");
			}
		}else {
			return new ModelAndView("redirect:/notLoggedIn");
		}
	}
	 * 
	 * */
	
	@RequestMapping(value = "/departmentDomain/listAllDepartments", method = RequestMethod.POST)
	public ModelAndView listEmployee(ModelAndView model) throws IOException {
		//this is allowed for admins only. But we do not have to check if role = admin because we use POST method and button for this path is deactivated for normal users
		List<Department> listDepartment = departmentService.getAll();
		model.addObject("listDepartment", listDepartment);
		model.setViewName("department-overview");
		return model;
	}
	
	@RequestMapping(value = "/departmentDomain/redirectToDomainPageDepartment", method = RequestMethod.GET)
	public String redirectToDomainPageDepartment() {
		return "redirect:/departmentDomain";
	}
	
	private boolean validateUserInputForDepartment(Department department, RedirectAttributes redirectAttributes) {
		boolean validInputs = true;
		
		//departmentName shall not be empty
		if(!department.getDepartmentName().isEmpty()) {
			redirectAttributes.addFlashAttribute("inputDepartmentName", "<p style=\"color:green;\">Input OK</p>");
		}else {
			validInputs = false;
			redirectAttributes.addFlashAttribute("inputDepartmentName", "<p style=\"color:red;\">Department name may not be empty</p>");
		}
		
		return validInputs;
	}
}
