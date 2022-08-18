package com.pig.licitai.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pig.licitai.exceptions.RegraDeNegocioException;
import com.pig.licitai.model.entity.Fornecedor;
import com.pig.licitai.model.repository.FornecedorRepositoy;
import com.pig.licitai.model.util.Atividade;
import com.pig.licitai.service.EnderecoService;
import com.pig.licitai.service.FornecedorService;

@Service
public class FornecedorServiceImpl implements FornecedorService {
	
	private FornecedorRepositoy repository;
	private ContaAcessoServiceImpl contaService;
	private AtividadeService atividadeService;
	private EnderecoService enderecoService;
	
	public FornecedorServiceImpl(FornecedorRepositoy repository, ContaAcessoServiceImpl contaService, AtividadeService atividadeService, EnderecoService enderecoService ) {
		super();
		this.repository = repository;
		this.contaService = contaService;
		this.atividadeService = atividadeService;
		this.enderecoService = enderecoService;
	}

	@Override
	public Fornecedor salvarFornecedor(Fornecedor fornecedor) {
		try {
			validarCnpj(fornecedor.getCnpj());
			fornecedor.setAtividade(atividadeService.validarAtividade(fornecedor.getAtividade()));
			contaService.salvarConta(fornecedor.getContaAcesso());
			enderecoService.salvarEndereco(fornecedor.getEndereco());
			return repository.save(fornecedor);
		} catch (Exception e) {
			throw new RegraDeNegocioException("Não foi possivel salvar fornecedor");
		}
		
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

	@Override
	public ArrayList<Fornecedor> obterPorAtividade(String codigoAtividade) {
		ArrayList<Fornecedor> fornecedoresRecuperados = repository.findByAtividade(codigoAtividade);
		if(fornecedoresRecuperados.isEmpty()) {
			throw new RegraDeNegocioException("Nenhum fornecedor encontrado");
		}
		return fornecedoresRecuperados;
	}

	@Override
	public ArrayList<Fornecedor> obterPorSegmento(String siglaSegmento) {
		ArrayList<Fornecedor> fornecedoresRecuperados = repository.findBySegmento(siglaSegmento);
		if(fornecedoresRecuperados.isEmpty()) {
			throw new RegraDeNegocioException("Nenhum fornecedor encontrado");
		}
		return fornecedoresRecuperados;
	}
	
	

}
