package com.ruffin.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruffin.dao.ContactRepository;
import com.ruffin.entities.Contact;

@RestController
public class ContactRestService {
	@Autowired
	private ContactRepository contactRepository;

	// Consulter la liste des contacts
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public List<Contact> getContacts() {
		return contactRepository.findAll();
	}

	// Consulter un id en particulier
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
	public Optional<Contact> getContacts(@PathVariable Long id) {
		return contactRepository.findById(id);
	}

	// Ajouter un contact
	@RequestMapping(value = "/contacts", method = RequestMethod.POST)
	public Contact save(@RequestBody Contact contact) {
		return contactRepository.save(contact);
	}

	// Supprimer un contact
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
	public boolean deleteContact(@PathVariable Long id) {
		contactRepository.deleteById(id);
		return true;
	}

	// Update un contact
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT)
	public Contact save(@PathVariable Long id, @RequestBody Contact contact) {
		contact.setId(id);
		return contactRepository.save(contact);
	}

	// rechercher la page contenant la list des contacts
	@RequestMapping(value = "/chercherContacts", method = RequestMethod.GET)
	public Page<Contact> chercher(@RequestParam(name = "motCle", defaultValue = "") String motCle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		Pageable sortedById = PageRequest.of(page, size, Sort.by("id"));
		return contactRepository.chercher("%" + motCle + "%", sortedById);
	}

}
