package com.sammySpring.aplicacion.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sammySpring.aplicacion.entity.Empresa;

public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, Long> {

    public Optional<Empresa> findByNombre(String username);

}