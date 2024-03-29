package com.pig.licitai.service;

import java.util.ArrayList;
import java.util.Optional;

import com.pig.licitai.model.entity.Fornecedor;

public interface FornecedorService {
	
	public Fornecedor salvarFornecedor(Fornecedor fornecedor);
	
	public Optional<Fornecedor> obterPorcnpj(String cnpj);
	
	public Fornecedor obterPorEmail(String email);
	
	public ArrayList<Fornecedor> obterPorAtividade(String codigoAtividade);
	
	public ArrayList<Fornecedor> obterPorSegmento(String siglaSegmento);
	
}
