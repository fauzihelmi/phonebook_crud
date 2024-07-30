package com.contact.springboot.payload.response;

import com.contact.springboot.model.Phonebook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PhonebookResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String notes;

    public PhonebookResponse(Phonebook phonebook) {
        this.firstName = phonebook.getFirstName();
        this.lastName = phonebook.getLastName();
        this.email = phonebook.getEmail();
        this.phone = phonebook.getPhone();
        this.address = phonebook.getAddress();
        this.notes = phonebook.getNotes();
    }
}
