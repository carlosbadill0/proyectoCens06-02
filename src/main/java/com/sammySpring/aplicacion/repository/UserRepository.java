package com.sammySpring.aplicacion.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sammySpring.aplicacion.entity.User;
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	public Optional<User> findByUsername(String username);
}
