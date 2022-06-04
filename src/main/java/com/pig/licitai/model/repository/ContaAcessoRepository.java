package com.pig.licitai.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.pig.licitai.model.util.ContaAcesso;

public interface ContaAcessoRepository extends JpaRepository<ContaAcesso, String>{

	public boolean existsByEmail(String email);
	
	public Optional<ContaAcesso> findByEmail(String email);
	
}
