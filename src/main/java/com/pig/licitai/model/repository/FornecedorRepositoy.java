package com.pig.licitai.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pig.licitai.model.entity.Fornecedor;

public interface FornecedorRepositoy extends JpaRepository<Fornecedor, String>{
	
	public boolean existsByCnpj(String cnpj);
	
	public Optional<Fornecedor> findByCnpj(String cnpj);
	
	@Query(value = "select * from tb_fornecedor forn, tb_conta cnt where cnt.email = forn.conta_acesso_email and cnt.email = :email", nativeQuery = true)
	public Optional<Fornecedor> findByEmail(@Param("email") String email);
	
 }
