package com.ntt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ntt.model.Persona;

@NoRepositoryBean
public interface IPersonaDAO extends JpaRepository<Persona, Integer>{

}
