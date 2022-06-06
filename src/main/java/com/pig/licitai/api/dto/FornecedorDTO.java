package com.pig.licitai.api.dto;


import com.pig.licitai.model.util.ContaAcesso;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FornecedorDTO {
	
	private String nome;
	private String cnpj;
	private long telefone;
	private ContaAcesso contaAcesso;
//	private Endereco endereco;
	
}
