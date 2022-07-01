package com.ntt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntt.model.Cliente;
import com.ntt.service.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteServ;
	
	@GetMapping( value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> listar(){
		List<Cliente> client = new ArrayList<>();
		try {
			client = clienteServ.listar();			
		} catch (Exception e) {
			// TODO: handle exception
			new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Cliente>>(client, HttpStatus.OK);
	}
	
	@PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> registrar(@RequestBody Cliente cli ){		
		Cliente client = new Cliente();
		try {
			client = clienteServ.registrar(cli);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Cliente>(client, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		return new ResponseEntity<Cliente>(client, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/listar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> listarId (@PathVariable("id") Integer id){		
		Cliente client = new Cliente();
		try {
			client = clienteServ.listarId(id);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Cliente>(client, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cliente>(client, HttpStatus.OK);	
		
	}
	
	@PutMapping(value = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> actualizar(@RequestBody Cliente cuenta) {			
		int resultado = 0;
		try {
			resultado = clienteServ.modificar(cuenta);			
		} catch (Exception e) {
			return new ResponseEntity<Integer>(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(resultado, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> eliminar(@PathVariable Integer id) {
		int resultado = 0;
		try {
			clienteServ.eliminar(id);
			resultado = 1;
		} catch (Exception e) {
			resultado = 0;
		}

		return new ResponseEntity<Integer>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarPageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Cliente>> listar(Pageable pageable){
		Page<Cliente> clients = null;
		try {			
			clients = clienteServ.listAllByPage(pageable);
			
		} catch (Exception e) {
			// TODO: handle exception
			new ResponseEntity<Page<Cliente>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Cliente>>(clients, HttpStatus.OK);		
	}
	

}
