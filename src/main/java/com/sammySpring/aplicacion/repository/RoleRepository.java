package com.sammySpring.aplicacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sammySpring.aplicacion.entity.Role;


@Repository
public interface RoleRepository  extends CrudRepository<Role, Long>{

}
