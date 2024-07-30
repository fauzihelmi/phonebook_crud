package com.contact.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contact.springboot.model.Phonebook;
import com.contact.springboot.service.PhonebookService;

@Controller
public class PhonebookController {

	@Autowired
	private PhonebookService phonebookService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		/*
		display list of phonebook
		*/
		return findPaginated(1, "firstName", "asc", model);
	}
	
	@GetMapping("/showNewPhonebookForm")
	public String showNewEmployeeForm(Model model) {
		/*
		create model attribute to bind form data
		*/
		Phonebook phonebook = new Phonebook();
		model.addAttribute("phonebook", phonebook);
		return "new_phonebook";
	}
	
	@PostMapping("/savePhonebook")
	public String savePhonebook(@ModelAttribute("phonebook") Phonebook phonebook) {
		/*
		save phonebook to database
		*/
		phonebookService.savePhonebook(phonebook);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

		/*
		get phonebook from the service
		*/
		Phonebook phonebook = phonebookService.getPhonebookById(id);

		/*
		set phonebook as a model attribute to pre-populate the form
		*/
		model.addAttribute("phonebook", phonebook);
		return "update_phonebook";
	}
	
	@GetMapping("/deletePhonebook/{id}")
	public String deletePhonebook(@PathVariable (value = "id") long id) {

		/*
		call delete phonebook method
		*/
		this.phonebookService.deletePhonebookById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		/*
		set pagination
		*/
		int pageSize = 5;

		Page<Phonebook> page = phonebookService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Phonebook> listPhonebook = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listPhonebook", listPhonebook);
		return "index";
	}
}
