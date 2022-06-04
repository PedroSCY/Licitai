package com.pig.licitai.service.impl;

import java.util.Optional;

import com.pig.licitai.exceptions.ErroAutenticacao;
import com.pig.licitai.exceptions.RegraDeNegocioException;
import com.pig.licitai.model.repository.ContaAcessoRepository;
import com.pig.licitai.model.util.ContaAcesso;
import com.pig.licitai.service.ContaAcessoService;

public class ContaAcessoServiceImpl implements ContaAcessoService{
	
	private ContaAcessoRepository repository;
	
	public ContaAcessoServiceImpl(ContaAcessoRepository repository ) {
		super();
		this.repository = repository;
	}
	
	@Override
	public ContaAcesso autenticar(String email, String senha) {
		Optional<ContaAcesso> contaAcesso = repository.findByEmail(email);

		if (!contaAcesso.isPresent()) {
			throw new ErroAutenticacao("Usuario não encontrado");
		}

		if (!contaAcesso.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha incorreta");
		}

		return contaAcesso.get();
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraDeNegocioException("Este E-mail já está em uso.");
		} 
		
	}
	
	public ContaAcesso salvarConta(ContaAcesso conta) {
		validarEmail(conta.getEmail());
		return repository.save(conta);
	}

}
