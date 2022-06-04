package com.pig.licitai.model.entity;

import com.pig.licitai.model.util.ContaAcesso;
import com.pig.licitai.model.util.Endereco;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Usuario {

	private String nome;
	
	private String cnpj;
	
	private long telefone;
	
	private ContaAcesso contaAcesso;
	
	private Endereco endereco;
}
