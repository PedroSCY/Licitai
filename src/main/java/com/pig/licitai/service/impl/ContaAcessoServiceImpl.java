package com.pig.licitai.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pig.licitai.exceptions.ErroAutenticacao;
import com.pig.licitai.exceptions.RegraDeNegocioException;
import com.pig.licitai.model.repository.ContaAcessoRepository;
import com.pig.licitai.model.util.ContaAcesso;
import com.pig.licitai.service.ContaAcessoService;

@Service
public class ContaAcessoServiceImpl implements ContaAcessoService{
	
	private ContaAcessoRepository repository;
	private PasswordEncoder encoder;
	
	public ContaAcessoServiceImpl(ContaAcessoRepository repository, PasswordEncoder encoder ) {
		super();
		this.repository = repository;
		this.encoder = encoder;
	}
	
	@Override
	public ContaAcesso autenticar(String email, String senha) {
		Optional<ContaAcesso> contaAcesso = repository.findByEmail(email);

		if (!contaAcesso.isPresent()) {
			throw new ErroAutenticacao("Usuario não encontrado");
		}
		
		boolean senhasBatem = encoder.matches(senha, contaAcesso.get().getSenha());

		if (!senhasBatem) {
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
		criptografarSenha(conta);
		return repository.save(conta);
	}
	
	public void criptografarSenha(ContaAcesso conta) {
		String senha = conta.getSenha();
		String senhaCripto = encoder.encode(senha);
		conta.setSenha(senhaCripto);
	}

}
