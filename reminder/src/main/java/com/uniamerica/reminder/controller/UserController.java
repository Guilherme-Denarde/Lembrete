package com.uniamerica.reminder.controller;

import com.uniamerica.reminder.dto.User;
import com.uniamerica.reminder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping()
	public ResponseEntity<List<User>> findAll() {
		try {
			return ResponseEntity.ok(service.findAll());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/add")
	public ResponseEntity<String> cadastrar(@RequestBody final User user){
		try {
			this.service.cadastrar(user);
			return ResponseEntity.ok("Registrado cadastrado com Sucesso");
		}
		catch (DataIntegrityViolationException e) {
			return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
		}
		catch (RuntimeException e){
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}
}