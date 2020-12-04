package com.ruffin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ruffin.dao.ContactRepository;
import com.ruffin.entities.Contact;

@SpringBootApplication
public class JeeSpringAngularApplication implements CommandLineRunner {
	@Autowired
	private ContactRepository contactRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JeeSpringAngularApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		contactRepository.save(new Contact("LUCE","Melanie",dateFormat.parse("27/03/1981"),"mLuce@hotmail.fr",0661345432, "Melanie.jpg"));
		contactRepository.save(new Contact("DUPOND","Julien",dateFormat.parse("03/04/1895"),"jDupond@yahoo.fr",0661345432, "Julien.jpg"));
		contactRepository.save(new Contact("MICHEL","Helene",dateFormat.parse("21/11/1920"),"hMichel@hotmail.fr",0660345232, "Helene.jpg"));
		contactRepository.save(new Contact("ROGER","Nicolas",dateFormat.parse("16/12/1989"),"nRoger@gmail.com",0651245432, "Nicolas.jpg"));
		contactRepository.findAll().forEach(c->{
			System.out.println(c.getNom());
		});
	}

}
