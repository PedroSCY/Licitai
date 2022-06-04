package com.pig.licitai.service;

import java.util.Optional;

import com.pig.licitai.model.entity.Fornecedor;

public interface FornecedorService {
	
	public Fornecedor salvarFornecedor(Fornecedor fornecedor);
	
	public Optional<Fornecedor> obterPorcnpj(String cnpj);
	
}
