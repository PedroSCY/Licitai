package com.pig.licitai.service.impl;

import org.springframework.stereotype.Service;

import com.pig.licitai.model.repository.EnderecoRepository;
import com.pig.licitai.model.util.Endereco;
import com.pig.licitai.service.EnderecoService;

@Service
public class EnderecoServiceImp implements EnderecoService{

	private EnderecoRepository repository;
	
	public EnderecoServiceImp(EnderecoRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Endereco salvarEndereco(Endereco endereco) {
		return repository.save(endereco);
	}

}
