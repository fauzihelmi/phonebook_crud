package com.contact.springboot.service;

import java.util.List;
import java.util.Optional;

import com.contact.springboot.repository.PhonebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.contact.springboot.model.Phonebook;

@Service
public class PhonebookServiceImpl implements PhonebookService {

	@Autowired
	private PhonebookRepository phonebookRepository;

	@Override
	public void savePhonebook(Phonebook phonebook) {
		/*
		check if phone number empty will be return error
		*/
		if (phonebook.getPhone() == null || phonebook.getPhone().isEmpty()) {
			throw new RuntimeException(" Phone number cannot be empty ");
		}

		Phonebook phoneNo = phonebookRepository.findByPhoneNo(phonebook.getPhone());
		/*
		check if phone number already registered will be return error
		*/
		if (phoneNo != null) {
			throw new RuntimeException(" Phone number already exist ");
		} else {
			phonebookRepository.save(phonebook);
		}
	}

	@Override
	public Phonebook getPhonebookById(long id) {
		Optional<Phonebook> optional = phonebookRepository.findById(id);
		Phonebook phonebook = null;
		if (optional.isPresent()) {
			phonebook = optional.get();
		} else {
			throw new RuntimeException(" Phonebook not found for id :: " + id);
		}
		return phonebook;
	}

	@Override
	public void deletePhonebookById(long id) {
		this.phonebookRepository.deleteById(id);
	}

	@Override
	public Page<Phonebook> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.phonebookRepository.findAll(pageable);
	}
}
