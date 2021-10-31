package com.joel.henz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.joel.henz.model.Employee;
import com.joel.henz.service.EmployeeService;
import com.joel.henz.service.LoginAndSessionService;


@Controller
public class MainController {
	
	@Autowired
	private LoginAndSessionService loginAndSessionService;
	
	@Autowired
	private EmployeeService employeeDaoService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String sayHello() {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			return "redirect:/welcome";
		}else {
			return "index";
		}
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String redirectToLoginPage() {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			return "redirect:/welcome";
		}else {
			return "login";
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RedirectView validateLogin(@RequestParam String userName, @RequestParam String password, RedirectAttributes redirectAttributes) {
		Employee emp = loginAndSessionService.validateUser(userName, password, employeeDaoService);
		
		if(emp != null) {
			//set session attributes
			session.setAttribute("username", userName);
			session.setAttribute("role", emp.getRole());
			session.setAttribute("userId", emp.getId());
			
			if(emp.getDepartment() == null) {
				session.setAttribute("departmentId", -1);
				session.setAttribute("departmentName", "no department assigned");
			}else {
				session.setAttribute("departmentId", emp.getDepartment().getId());
				session.setAttribute("departmentName", emp.getDepartment().getDepartmentName());
			}

			redirectAttributes.addFlashAttribute("userName", userName);
			return new RedirectView("welcome");
			
		}else {
			//error message will appear on login.jsp
			redirectAttributes.addFlashAttribute("errorMessage", "Invalid credentials. Check username and / or password");
			return new RedirectView("login");
		}
	}
	
	@RequestMapping(value = "/welcome", method=RequestMethod.GET)
	public String showWelcomePage() {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			return "welcome";
		}else {
			return "redirect:/notLoggedIn";
		}
	}
	
	@RequestMapping(value = "/redirectToHome", method=RequestMethod.GET)
	public String redirectToIndexPage() {
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.POST)
	public String logout() {
		//remove all attributes
		session.invalidate();
		return "logout";
	}
	
	@RequestMapping(value = "/notLoggedIn", method=RequestMethod.GET)
	public String notLoggedIn() {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			return "redirect:/welcome";
		}else {
			return "not-logged-in";
		}
	}
	
	@RequestMapping(value = "/actionNotAllowed", method = RequestMethod.GET)
	public String actionNotAllowed() {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			return "action-not-allowed";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/operationSuccessfull", method = RequestMethod.GET)
	public String operationSuccessfull() {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			return "operation_success";
		}else {
			return "redirect:/notLoggedIn";
		}
	}
	
	@RequestMapping(value = "/redirectToWelcome", method = RequestMethod.GET)
	public String redirectToWelcomePageAfterOperation() {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			return "welcome";
		}else {
			return "redirect:/notLoggedIn";
		}
	}
}
