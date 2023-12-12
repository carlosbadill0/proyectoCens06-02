package com.sammySpring.aplicacion.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



import com.sammySpring.aplicacion.entity.Empresa;
import com.sammySpring.aplicacion.service.EmpresaService;



@Controller
public class EmpresaController {

    @Autowired
    EmpresaService empresaServicio;

    @GetMapping("/empresaForm")
    public String empresaForm(Model model) {
		model.addAttribute("empresaForm", new Empresa());
		model.addAttribute("empresaList", empresaServicio.getALLEmpresa());
		model.addAttribute("listTab","active");
		return "empresa-form/empresa-view";
	}
    
    @PostMapping("/empresaForm")
	public String createEmpresa(@Valid @ModelAttribute("empresaForm")Empresa empresa, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("empresaForm", empresa);
			model.addAttribute("formTab","active");
		}else {
			try {
				empresaServicio.createEmpresa(empresa);
				model.addAttribute("empresaForm", new Empresa());
				model.addAttribute("listTab","active");
				
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("empresaForm", empresa);
				model.addAttribute("formTab","active");
				model.addAttribute("empresaList", empresaServicio.getALLEmpresa());
			}
		}
		model.addAttribute("empresaList", empresaServicio.getALLEmpresa());
		return "empresa-form/empresa-view";
	}

    @GetMapping("/editEmpresa/{id}")
	public String getEditEmpresaForm(Model model, @PathVariable(name = "id")long id)throws Exception{
		Empresa userToEdit = empresaServicio.getEmpresaById(id);
		
		model.addAttribute("empresaForm", userToEdit);
		model.addAttribute("empresaList", empresaServicio.getALLEmpresa());
		model.addAttribute("formTab","active");
		model.addAttribute("editMode","true");
		
		return "empresa-form/empresa-view";
	}


    @PostMapping("/editEmpresa")
	public String postEditEmpresaForm(@Valid @ModelAttribute("empresaForm")Empresa empresa, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("empresaForm", empresa);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode","true");
		}else {
			try {
				empresaServicio.updateEmpresa(empresa);
				model.addAttribute("empresaForm", new Empresa());
				model.addAttribute("listTab","active");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("empresaForm", empresa);
				model.addAttribute("formTab","active");
				model.addAttribute("empresaList", empresaServicio.getALLEmpresa());
				model.addAttribute("editMode","true");
			}
		}
		model.addAttribute("empresaList", empresaServicio.getALLEmpresa());

		return "empresa-form/empresa-view";
	}


    @GetMapping("/empresaForm/cancel")
	public String cancelEditEmpresa(ModelMap model) {
		return "redirect:/empresaForm";
	}

    @GetMapping("/deleteEmpresa/{id}")
	public String deleteEmpresa(Model model, @PathVariable(name = "id")long id) {
		try {
			empresaServicio.deleteEmpresa(id);
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return empresaForm(model);
	}

}
