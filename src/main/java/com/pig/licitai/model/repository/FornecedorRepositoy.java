package com.pig.licitai.model.repository;

import java.util.ArrayList;
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
	
	@Query(value = "select * from tb_fornecedor forn, tb_atividade atv where forn.atividade_fk = atv.codigo_atividade and forn.atividade_fk = :codigoAtividade", nativeQuery = true)
	public ArrayList<Fornecedor> findByAtividade(@Param("codigoAtividade") String codigoAtividade);
	
	@Query(value = "select * from tb_fornecedor forn, tb_atividade atv where forn.atividade_fk = atv.codigo_atividade and atv.sigla_segmento = :siglaSegmento", nativeQuery = true)
	public ArrayList<Fornecedor> findBySegmento(@Param("siglaSegmento")String siglaSegmento);
	
 }
