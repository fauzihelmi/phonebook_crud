package com.contact.springboot.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PhonebookRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String notes;
}
