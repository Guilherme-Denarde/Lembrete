package com.uniamerica.reminder.service;

import java.util.List;

import com.uniamerica.reminder.dto.User;
import com.uniamerica.reminder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	@Transactional(rollbackFor = Exception.class)
	public void cadastrar(@RequestBody final User user){
		Assert.isTrue(user.getName().length() > 2, "O nome está faltando");
		this.repository.save(user);
	}
}