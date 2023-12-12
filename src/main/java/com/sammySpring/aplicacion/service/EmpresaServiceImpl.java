package com.sammySpring.aplicacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sammySpring.aplicacion.entity.Empresa;
import com.sammySpring.aplicacion.repository.EmpresaRepository;


@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
	public Iterable<Empresa> getALLEmpresa() {
		return empresaRepository.findAll();
	}

    @Override
    @Transactional(readOnly = true)
    public List<Empresa> findAll() {
        return (List<Empresa>) empresaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Empresa> findAll(Pageable pageable) {
        return empresaRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    @Override
	@Transactional(readOnly = true)
	public Empresa findOne(Long id) {
		return empresaRepository.findById(id).orElse(null);
	}

    @Override
    @Transactional
    public void delete(Long id) {
        empresaRepository.deleteById(id);
    }

    @Override
	public Empresa createEmpresa(Empresa empresa) throws Exception {
		empresa = empresaRepository.save(empresa);
		return empresa;
	}

    @Override
	public Empresa getEmpresaById(Long id) throws Exception {
		return empresaRepository.findById(id).orElseThrow(()-> new Exception("El usuario no existe."));
	}

    @Override
	public Empresa updateEmpresa(Empresa fromEmpresa)throws Exception {
	    Empresa toEmpresa = getEmpresaById(fromEmpresa.getId());
	    mapUser(fromEmpresa,toEmpresa);
	    return empresaRepository.save(toEmpresa);
	}
	protected void mapUser(Empresa from,Empresa to) {
		to.setNombre(from.getNombre());
		to.setContacto(from.getContacto());
		to.setEmail(from.getEmail());
		to.setDireccion(from.getDireccion());
		to.setNota(from.getNota());
	}

    @Override
	public void deleteEmpresa(Long id) throws Exception {
	  Empresa empresa = getEmpresaById(id) ;
	  empresaRepository.delete(empresa);
	}



}
