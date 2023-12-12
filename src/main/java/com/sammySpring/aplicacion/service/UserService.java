package com.sammySpring.aplicacion.service;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import com.sammySpring.aplicacion.entity.User;


public interface UserService {

	public List<User>findAll();

	public Iterable<User> getALLUsers();

	public Page<User> findAll(Pageable pageable);

	public void save(User empleado);

	public User findOne(Long id);

	public void delete(Long id);

	public User createUser(User user) throws Exception;
	
	public User getUserById(Long id)throws Exception;
	
	public User updateUser(User user)throws Exception;
	
	public void deleteUser(Long id) throws Exception;

}
