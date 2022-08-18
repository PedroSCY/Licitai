package com.pig.licitai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pig.licitai.exceptions.RegraDeNegocioException;
import com.pig.licitai.model.repository.AtividadeRepository;
import com.pig.licitai.model.util.Atividade;

@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository repository;
	
	public boolean verificarExistencia(String codigoAtividade) {	
		if(repository.existsById(codigoAtividade)){
			return true;
		}
		return false;
	}
	
	public Atividade cadastrarAtividade(Atividade atividade) {
		return repository.save(atividade);
	}
	
	public Atividade validarAtividade(Atividade atividade) {
		if(verificarExistencia(atividade.getCodigoAtividade())) {
			System.out.println("Entrei IF");
			return repository.findById(atividade.getCodigoAtividade()).get();
		}else {
			System.out.println("Entrei Else");
			if(atividade.getNomeAtividade().isEmpty()) {
				throw new RegraDeNegocioException("Nome da Atividade não pode estar vazio");
			}
			if(atividade.getSiglaSegmento().length() != 1) {
				throw new RegraDeNegocioException("A Sigla deve estar no intervalo de [A-U], contendo uma unica letra.");
			}
			if(atividade.getCodigoAtividade().isEmpty()) {
				throw new RegraDeNegocioException("O codigo da Atividade não pode estar vazio");
			}
	
			return cadastrarAtividade(atividade);
			
		}
	}
	
}
