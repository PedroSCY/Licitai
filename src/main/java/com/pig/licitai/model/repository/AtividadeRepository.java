package com.pig.licitai.model.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pig.licitai.model.util.Atividade;


@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, String> {
	
	public ArrayList<Atividade> findBySiglaSegmento(String siglaSegmento);
	
	public Optional<Atividade> findById(String codigoAtividade);

}
