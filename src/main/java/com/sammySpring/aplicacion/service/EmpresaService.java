package com.sammySpring.aplicacion.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sammySpring.aplicacion.entity.Empresa;


public interface EmpresaService {

    public List<Empresa> findAll();

    public Page<Empresa> findAll(Pageable pageable);

    public void save(Empresa empresa);

    public Empresa findOne(Long id);

    public void delete(Long id);

    public Empresa createEmpresa(Empresa empresa) throws Exception;

    public Iterable<Empresa> getALLEmpresa();

    public Empresa getEmpresaById(Long id)throws Exception;

    public Empresa updateEmpresa(Empresa empresa)throws Exception;

    public void deleteEmpresa(Long id) throws Exception;

}
