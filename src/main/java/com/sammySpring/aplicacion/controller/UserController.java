package com.sammySpring.aplicacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sammySpring.aplicacion.entity.User;
import com.sammySpring.aplicacion.repository.RoleRepository;
import com.sammySpring.aplicacion.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	
	@GetMapping("/userForm")
	public String userForm(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getALLUsers());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("listTab","active");
		return "user-form/user-view";
	}
	
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab","active");
		}else {
			try {
				userService.createUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab","active");
				
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", userService.getALLUsers());
				model.addAttribute("roles",roleRepository.findAll());
			}
		}
		model.addAttribute("userList", userService.getALLUsers());
		model.addAttribute("roles",roleRepository.findAll());
		
		return "user-form/user-view";
	}
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name = "id")long id)throws Exception{
		User userToEdit = userService.getUserById(id);
		
		model.addAttribute("userForm", userToEdit);
		model.addAttribute("userList", userService.getALLUsers());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("formTab","active");
		model.addAttribute("editMode","true");
		
		return "user-form/user-view";
	}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode","true");
		}else {
			try {
				userService.updateUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab","active");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", userService.getALLUsers());
				model.addAttribute("roles",roleRepository.findAll());
				model.addAttribute("editMode","true");
			}
		}
		model.addAttribute("userList", userService.getALLUsers());
		model.addAttribute("roles",roleRepository.findAll());
		
		return "user-form/user-view";
	}
	
	@GetMapping("/userForm/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/userForm";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name = "id")long id) {
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return userForm(model);
	}

}
