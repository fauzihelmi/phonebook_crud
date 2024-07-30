package com.contact.springboot.service;

import com.contact.springboot.payload.request.PhonebookRequest;
import com.contact.springboot.payload.response.PhonebookResponse;
import org.springframework.data.domain.Page;

import com.contact.springboot.model.Phonebook;

import java.util.List;

public interface PhonebookService {
	void savePhonebook(Phonebook phonebook);
	Phonebook getPhonebookById(long id);
	void deletePhonebookById(long id);
	Phonebook updatePhonebook(long id, PhonebookRequest phonebookRequest);
	Phonebook newRegister(PhonebookRequest phonebookRequest);
	List<PhonebookResponse> getAllPhonebooks();
	Page<Phonebook> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
