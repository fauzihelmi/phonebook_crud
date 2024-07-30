package com.contact.springboot.service;

import org.springframework.data.domain.Page;

import com.contact.springboot.model.Phonebook;

public interface PhonebookService {
	void savePhonebook(Phonebook phonebook);
	Phonebook getPhonebookById(long id);
	void deletePhonebookById(long id);
	Page<Phonebook> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
