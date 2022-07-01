package com.ntt.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntt.model.Cliente;



@Repository
public interface IClienteDAO extends JpaRepository<Cliente, Integer>{

}
