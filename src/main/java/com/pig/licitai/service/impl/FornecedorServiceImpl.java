package com.pig.licitai.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pig.licitai.exceptions.RegraDeNegocioException;
import com.pig.licitai.model.entity.Fornecedor;
import com.pig.licitai.model.repository.FornecedorRepositoy;
import com.pig.licitai.service.FornecedorService;

@Service
public class FornecedorServiceImpl implements FornecedorService {
	
	private FornecedorRepositoy repository;
	private ContaAcessoServiceImpl contaService;
	
	public FornecedorServiceImpl(FornecedorRepositoy repository, ContaAcessoServiceImpl contaService) {
		super();
		this.repository = repository;
		this.contaService = contaService;
	}

	@Override
	public Fornecedor salvarFornecedor(Fornecedor fornecedor) {
		validarCnpj(fornecedor.getCnpj());
		contaService.salvarConta(fornecedor.getContaAcesso());
		return repository.save(fornecedor);
		
	}

	@Override
	public Optional<Fornecedor> obterPorcnpj(String cnpj) {
		return repository.findByCnpj(cnpj);
	}
	
	public void validarCnpj(String cnpj) {
		boolean existe = repository.existsByCnpj(cnpj);
		if(existe) {
			throw new RegraDeNegocioException("Este CNPJ já está cadastrado");
		}
	}

	@Override
	public Fornecedor obterPorEmail(String email) {
		Optional<Fornecedor> fornecedor = repository.findByEmail(email);
		if(!fornecedor.isPresent())
			throw new RegraDeNegocioException("Usuario não encontrado");
		return repository.findByEmail(email).get();
	}
	
	

}
