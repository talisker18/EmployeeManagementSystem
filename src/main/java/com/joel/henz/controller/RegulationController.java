package com.joel.henz.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.joel.henz.dto.CommentForRegulationByUserDto;
import com.joel.henz.dto.RegulationDepartmentDto;
import com.joel.henz.helper.DataManipulationHelper;
import com.joel.henz.helper.FilterHelper;
import com.joel.henz.model.Comment;
import com.joel.henz.model.Department;
import com.joel.henz.model.Employee;
import com.joel.henz.model.Regulation;
import com.joel.henz.service.CommentService;
import com.joel.henz.service.IDaoService;
import com.joel.henz.service.LoginAndSessionService;

@Controller
public class RegulationController {
	
	@Autowired
	private LoginAndSessionService loginAndSessionService;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IDaoService<Regulation> regulationService;
	
	@Autowired
	private IDaoService<Department> departmentService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private IDaoService<Employee> employeeService;
	
	@RequestMapping(value = "/regulationDomain", method = RequestMethod.GET)
	public String enterRegulationDomain() {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			return "regulation-page";
		}else {
			return "redirect:/notLoggedIn";
		}
	}
	
	@RequestMapping(value = "/regulationDomain/newRegulation", method = RequestMethod.GET)
	public ModelAndView newRegulation(@ModelAttribute RegulationDepartmentDto regulation, ModelAndView model) { 
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			//check first if user has admin role
			if(session.getAttribute("role").equals("admin")) {
				List<Department> departmentList = departmentService.getAll();
				model.addObject("departmentList", departmentList);
				model.setViewName("RegulationForm");
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
	
	@RequestMapping(value = "/regulationDomain/newRegulation/saveRegulation", method = RequestMethod.POST)
	public RedirectView saveRegulation(@ModelAttribute RegulationDepartmentDto regulationDepartmentDto, RedirectAttributes redirectAttributes) {
		Regulation regulation = regulationDepartmentDto.createRegulation(departmentService);
		
		if(validateUserInputForRegulation(regulationDepartmentDto, redirectAttributes)) {
			if (regulationDepartmentDto.getRegulationId() == 0) {
				
				regulationService.add(regulation);
				redirectAttributes.addFlashAttribute("successMessage", "successfully added new regulation");
			} else {
				regulationService.update(regulation);
				redirectAttributes.addFlashAttribute("successMessage", "successfully updated regulation with id"+regulation.getId());
			}
			
			return new RedirectView("/EmployeeManagementSystem/operationSuccessfull");
			
		}else {
			redirectAttributes.addFlashAttribute("regulationDepartmentDto", regulationDepartmentDto);
			return new RedirectView("/EmployeeManagementSystem/regulationDomain/newRegulation"); //use redirect beginning with / to set completely new path from beginning
		}
	}
	
	@RequestMapping(value = "/regulationDomain/listAllRegulations", method = RequestMethod.POST)
	public ModelAndView listRegulation(ModelAndView model) throws IOException {
		List<Regulation> listRegulation = regulationService.getAll();
		model.addObject("listRegulation", listRegulation);
		model.setViewName("regulation-overview");
		return model;
	}
	
	/**
	 * the following blocks could be used to edit / delete regulation. But its not in the business requirements and therefore not implemented
	 * 
	 * @RequestMapping(value = "/regulationDomain/chooseRegulation", method = RequestMethod.POST)
	public ModelAndView chooseRegulationToDelete(ModelAndView model) {
		List<Regulation> listRegulation = regulationService.getAll();
		model.addObject("listRegulation", listRegulation);
		model.setViewName("delete-or-update-regulation");
		return model;
	}
	
	@RequestMapping(value = "/regulationDomain/editRegulation", method = RequestMethod.GET)
	public ModelAndView editRegulation(HttpServletRequest request) {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			if(session.getAttribute("role").equals("admin")) {
				int regulationId = Integer.parseInt(request.getParameter("id"));
				Regulation regulation = regulationService.get(regulationId);
				
				RegulationDepartmentDto regDepDto = new RegulationDepartmentDto(
						regulationId, 
						regulation.getType(), 
						regulation.getDetails(),
						regulation.getCreationDate(),
						regulation.getDepartment().getId()
				);
				
				ModelAndView model = new ModelAndView("RegulationForm");
				model.addObject("regulationDepartmentDto", regDepDto);
				
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
	
	@RequestMapping(value = "/regulationDomain/deleteRegulation", method = RequestMethod.GET)
	public RedirectView deleteRegulation(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			if(session.getAttribute("role").equals("admin")) {
				int regulationId = Integer.parseInt(request.getParameter("id"));
				regulationService.delete(regulationId);
				redirectAttributes.addFlashAttribute("successMessage", "successfully deleted regulation with id "+regulationId);
				return new RedirectView("/EmployeeManagementSystem/operationSuccessfull");
			}else {
				return new RedirectView("/EmployeeManagementSystem/actionNotAllowed");
			}
		}else {
			return new RedirectView("/EmployeeManagementSystem/notLoggedIn");
		}
	}
	 * 
	 * */
	
	@RequestMapping(value = "/regulationDomain/showRegulationsForDepartment", method = RequestMethod.POST)
	public ModelAndView showRegulationsForDepartment(ModelAndView model) throws IOException {
		Integer departmentIdOfCurrentUser = (Integer) session.getAttribute("departmentId");
		int departmentIdOfCurrentUserUnboxed = departmentIdOfCurrentUser;
		
		List<Regulation> listRegulationForDepartment = FilterHelper.filterByPredicate(
				regulationService.getAll(), 
				FilterHelper.filterRegulationByDepartmentId(departmentIdOfCurrentUserUnboxed)
		);
		
		if(departmentIdOfCurrentUserUnboxed == -1) {
			model.addObject("titleMessage", "no department assigned for your user");
		}else {
			model.addObject("titleMessage", "Regulations for department \""+session.getAttribute("departmentName")+"\"");
		}
		
		model.addObject("listRegulationForDepartment", listRegulationForDepartment);
		
		model.setViewName("regulation-overview-dep");
		return model;
	}
	
	@RequestMapping(value = "/regulationDomain/showRegulationsForDepartmentAndAddComment", method = RequestMethod.POST)
	public ModelAndView showRegulationsForDepartmentAndAddComment(ModelAndView model) throws IOException {
		Integer departmentIdOfCurrentUser = (Integer) session.getAttribute("departmentId");
		int departmentIdOfCurrentUserUnboxed = departmentIdOfCurrentUser;
		
		List<Regulation> listRegulationForDepartment = FilterHelper.filterByPredicate(
				regulationService.getAll(), 
				FilterHelper.filterRegulationByDepartmentId(departmentIdOfCurrentUserUnboxed)
		);
		
		if(departmentIdOfCurrentUserUnboxed == -1) {
			model.addObject("titleMessage", "no department assigned for your user");
		}else {
			model.addObject("titleMessage", "Regulations for department \""+session.getAttribute("departmentName")+"\"");
		}
		
		List<CommentForRegulationByUserDto> commentList = new ArrayList<CommentForRegulationByUserDto>();
		
		Integer userIdOfCurrentUser = (Integer) session.getAttribute("userId");
		int userIdOfCurrentUserUnboxed = userIdOfCurrentUser;
		
		for(Regulation regulation: listRegulationForDepartment) {
			Comment comment = this.commentService.getByRegulationIdAndUserId(regulation.getId(), userIdOfCurrentUserUnboxed);
			CommentForRegulationByUserDto commentDto = CommentForRegulationByUserDto.createCommentDtoWithRegulation(userIdOfCurrentUserUnboxed, regulation);
			
			if(comment != null) {
				commentDto.setCommentText(comment.getText());
				commentDto.setCommentCreationDate(comment.getCreationDate());
				commentDto.setCommentId(comment.getId());
			}
			
			commentList.add(commentDto);
		}
		
		model.addObject("commentList", commentList);
		
		model.setViewName("regulation-overview-dep-comment");
		return model;
	}
	
	@RequestMapping(value = "/regulationDomain/addComment", method = RequestMethod.GET)
	public ModelAndView addComment(HttpServletRequest request, ModelAndView model, @ModelAttribute CommentForRegulationByUserDto commentDto) {
		
		if(loginAndSessionService.checkIfUserIsLoggedIn(session)) {
			if(request.getParameter("regulationId") != null && request.getParameter("commentId") != null) {
				String regulationIdAsString = request.getParameter("regulationId");
				int regulationId = Integer.parseInt(regulationIdAsString);
				Regulation regulation = this.regulationService.get(regulationId);
				
				int userId = (int) session.getAttribute("userId");
				int commentId = Integer.parseInt(request.getParameter("commentId"));
				Comment comment = this.commentService.get(commentId);
				commentDto= CommentForRegulationByUserDto.createCommentDtoWithRegulation(userId, regulation);
				commentDto.setCommentId(commentId);
				
				if(comment != null) {
					commentDto.setCommentText(comment.getText());
					commentDto.setCommentCreationDate(comment.getCreationDate());
				}
				
				model.addObject("commentDto", commentDto); //here we have to add the commentDto to model
			}
			
			model.setViewName("CommentForm");
			return model;
		}else {
			return new ModelAndView("redirect:/notLoggedIn");
		}
	}
	
	@RequestMapping(value = "/regulationDomain/saveComment", method = RequestMethod.POST)
	public RedirectView saveComment(@ModelAttribute CommentForRegulationByUserDto commentDto, RedirectAttributes redirectAttributes) {
		if(validateUserInputForComment(commentDto, redirectAttributes)) {
			if (commentDto.getCommentId() == 0) {
				Comment comment = Comment.createCommentWithDto(commentDto, this.regulationService);
				commentService.add(comment);
				
				DataManipulationHelper.checkIfStateOfRegulationHasToBeChanged(commentDto.getRegulationId(), regulationService, commentService, employeeService);
				
				redirectAttributes.addFlashAttribute("successMessage", "successfully added new comment");
			} else {
				Comment comment = Comment.createCommentWithDto(commentDto, this.regulationService);
				commentService.update(comment);
				redirectAttributes.addFlashAttribute("successMessage", "successfully updated comment with id "+comment.getId());
			}
			
			return new RedirectView("/EmployeeManagementSystem/operationSuccessfull");
			
		}else {
			redirectAttributes.addFlashAttribute("commentDto", commentDto);
			return new RedirectView("/EmployeeManagementSystem/regulationDomain/addComment"); //use redirect beginning with / to set completely new path from beginning
		}
	}
	
	@RequestMapping(value = "/regulationDomain/redirectToDomainPageRegulation", method = RequestMethod.GET)
	public String redirectToDomainPageRegulation() {
		return "redirect:/regulationDomain";
	}
	
	private boolean validateUserInputForRegulation(RegulationDepartmentDto reg, RedirectAttributes redirectAttributes) {
		boolean validInputs = true;  
		
		//fields shall not be empty
		if(!reg.getType().isEmpty()) {
			redirectAttributes.addFlashAttribute("inputRegulationType", "<p style=\"color:green;\">Input OK</p>");
		}else {
			validInputs = false;
			redirectAttributes.addFlashAttribute("inputRegulationType", "<p style=\"color:red;\">Regulation type may not be empty</p>");
		}
		
		if(!reg.getDetails().isEmpty()) {
			redirectAttributes.addFlashAttribute("inputRegulationDetails", "<p style=\"color:green;\">Input OK</p>");
		}else {
			validInputs = false;
			redirectAttributes.addFlashAttribute("inputRegulationDetails", "<p style=\"color:red;\">Regulation details may not be empty</p>");
		}
		
		if(reg.getCreationDate() == null) {
			validInputs = false;
			redirectAttributes.addFlashAttribute("inputRegulationCreationDate", "<p style=\"color:red;\">Regulation creation date must be filled in</p>");
		}else {
			redirectAttributes.addFlashAttribute("inputRegulationCreationDate", "<p style=\"color:green;\">Input OK</p>");
		}
		
		return validInputs;
	}
	
	private boolean validateUserInputForComment(CommentForRegulationByUserDto comm, RedirectAttributes redirectAttributes) {
		boolean validInputs = true;  
		
		//fields shall not be empty
		if(!comm.getCommentText().isEmpty()) {
			redirectAttributes.addFlashAttribute("inputCommentText", "<p style=\"color:green;\">Input OK</p>");
		}else {
			validInputs = false;
			redirectAttributes.addFlashAttribute("inputCommentText", "<p style=\"color:red;\">Comment text may not be empty</p>");
		}
		
		if(comm.getCommentCreationDate() == null) {
			validInputs = false;
			redirectAttributes.addFlashAttribute("inputCommentCreationDate", "<p style=\"color:red;\">Comment creation date must be filled in</p>");
		}else {
			redirectAttributes.addFlashAttribute("inputCommentCreationDate", "<p style=\"color:green;\">Input OK</p>");
		}
		
		return validInputs;
	}
}
