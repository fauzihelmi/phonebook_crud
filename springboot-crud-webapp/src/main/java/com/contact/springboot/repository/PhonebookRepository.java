package com.contact.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contact.springboot.model.Phonebook;

@Repository
public interface PhonebookRepository extends JpaRepository<Phonebook, Long>{

    @Query(
            value = "SELECT * FROM phonebook WHERE phone_no = :phone",
            nativeQuery = true)
    Phonebook findByPhoneNo(@Param("phone") String phone);

}
