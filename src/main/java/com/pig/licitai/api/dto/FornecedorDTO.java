package com.pig.licitai.api.dto;


import com.pig.licitai.model.util.Atividade;
import com.pig.licitai.model.util.ContaAcesso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {
	
	private String nome;
	private String cnpj;
	private long telefone;
	private ContaAcesso contaAcesso;
//	private Atividade atividade;
//	private Endereco endereco;
	
}
