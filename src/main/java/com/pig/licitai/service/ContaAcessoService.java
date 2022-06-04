package com.pig.licitai.service;

import com.pig.licitai.model.util.ContaAcesso;

public interface ContaAcessoService {

	public ContaAcesso autenticar(String email, String senha);
	
	public void validarEmail(String email);
}
