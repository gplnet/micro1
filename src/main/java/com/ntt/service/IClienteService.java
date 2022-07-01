package com.ntt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ntt.model.Cliente;




public interface IClienteService {
	
	Cliente registrar(Cliente cliente);
	int modificar(Cliente cliente);
	void eliminar(Integer id);
	Cliente listarId(int id);
	List<Cliente> listar();
	Page<Cliente> listAllByPage(Pageable pageable);

}
