package com.uniamerica.reminder.service;

import java.util.List;
import java.util.stream.Collectors;

import com.uniamerica.reminder.dto.UserDTO;
import com.uniamerica.reminder.entity.User;
import com.uniamerica.reminder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> findAll() {
		return userRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	public UserDTO save(UserDTO userDto) {
		User user = convertToEntity(userDto);
		user = userRepository.save(user);
		return convertToDTO(user);
	}

	private User convertToEntity(UserDTO userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		return user;
	}

	private UserDTO convertToDTO(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		return userDto;
	}
}
