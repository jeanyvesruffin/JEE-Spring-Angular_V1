package com.ruffin.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ruffin.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	@Query("select contact from Contact contact where contact.nom like :x")
	public Page<Contact> chercher(@Param("x") String motCle, Pageable pageable);

}
