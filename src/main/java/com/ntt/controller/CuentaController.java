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

import com.ntt.model.Cuenta;
import com.ntt.service.ICuenteService;




@RestController
@RequestMapping("/cuentas")
public class CuentaController {
	
	@Autowired
	private ICuenteService cuentaServ;
	
	
	@GetMapping( value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cuenta>> listar(){
		List<Cuenta> cuenta = new ArrayList<>();
		try {
			cuenta = cuentaServ.listar();			
		} catch (Exception e) {
			// TODO: handle exception
			new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Cuenta>>(cuenta, HttpStatus.OK);
	}
	
	@PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cuenta> registrar(@RequestBody Cuenta cuenta ){		
		Cuenta account = new Cuenta();
		try {
			account = cuentaServ.registrar(cuenta);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Cuenta>(account, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		return new ResponseEntity<Cuenta>(account, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/listar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cuenta> listarId (@PathVariable("id") Integer id){		
		Cuenta account = new Cuenta();
		try {
			account = cuentaServ.listarId(id);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Cuenta>(account, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cuenta>(account, HttpStatus.OK);	
		
	}
	
	
	@GetMapping(value="/getCuentaByNumber/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cuenta> getCuentaByNumber (@PathVariable("id") String id){		
		Cuenta account = new Cuenta();
		try {
			account = cuentaServ.getCuentaByNumber(id);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Cuenta>(account, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cuenta>(account, HttpStatus.OK);	
		
	}
	
	
	@PutMapping(value = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> actualizar(@RequestBody Cuenta cuenta) {			
		int resultado = 0;
		try {
			resultado = cuentaServ.modificar(cuenta);			
		} catch (Exception e) {
			return new ResponseEntity<Integer>(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(resultado, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> eliminar(@PathVariable Integer id) {
		int resultado = 0;
		try {
			cuentaServ.eliminar(id);
			resultado = 1;
		} catch (Exception e) {
			resultado = 0;
		}

		return new ResponseEntity<Integer>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarPageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Cuenta>> listar(Pageable pageable){
		Page<Cuenta> account = null;
		try {			
			account = cuentaServ.listAllByPage(pageable);
			
		} catch (Exception e) {
			// TODO: handle exception
			new ResponseEntity<Page<Cuenta>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Cuenta>>(account, HttpStatus.OK);		
	}
	

}
