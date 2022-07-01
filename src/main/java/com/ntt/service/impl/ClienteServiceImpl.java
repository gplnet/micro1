package com.ntt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ntt.dao.IClienteDAO;
import com.ntt.model.Cliente;

import com.ntt.service.IClienteService;


@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteDAO dao;

	@Override
	public Cliente registrar(Cliente cliente) {
		// TODO Auto-generated method stub		
		return dao.save(cliente);
	}

	@Override
	public int modificar(Cliente cliente) {
		// TODO Auto-generated method stub
		int rpsta = 0;
		rpsta = dao.save(cliente)!= null ? cliente.getClienteId() : 0;
		return rpsta > 0 ? 0 : 1;
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public Cliente listarId(int id) {
		// TODO Auto-generated method stub
		return (Cliente) dao.findById(id).get();
	}

	@Override
	public List<Cliente> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Page<Cliente> listAllByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findAll(pageable);
	}

}
