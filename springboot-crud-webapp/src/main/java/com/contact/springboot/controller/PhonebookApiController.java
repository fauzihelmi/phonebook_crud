package com.contact.springboot.controller;

import com.contact.springboot.exception.BadRequestException;
import com.contact.springboot.exception.ResourceNotFoundException;
import com.contact.springboot.payload.request.PhonebookRequest;
import com.contact.springboot.service.PhonebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PhonebookApiController {

    @Autowired
    private PhonebookService phonebookService;

    @GetMapping(value = "/getPhonebookList")
    public ResponseEntity<?> getListPhonebook() {
        return ResponseEntity.ok((phonebookService.getAllPhonebooks()));
    }

    @GetMapping(value = "/getPhonebook/{id}")
    public ResponseEntity<?> getPhonebookById(@PathVariable long id) {
        try {
            return ResponseEntity.ok((phonebookService.getPhonebookById(id)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/newPhonebook")
    public ResponseEntity<?> saveTask(@RequestBody PhonebookRequest phonebookRequest) {
        try {
            return ResponseEntity.ok( phonebookService.newRegister(phonebookRequest));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/updatePhonebook/{id}")
    public ResponseEntity<?> updatePhonebook(@PathVariable long id, @RequestBody PhonebookRequest phonebookRequest) {
        try {
            return ResponseEntity.ok( phonebookService.updatePhonebook(id, phonebookRequest));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletePhonebook/{id}")
    public ResponseEntity<?> deletePhonebook(@PathVariable long id) {
        try {
            phonebookService.deletePhonebookById(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
