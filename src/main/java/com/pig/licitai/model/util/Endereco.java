package com.pig.licitai.model.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Endereco {

	private String logradouro;
	
	private int numero;
	
	private String bairro;
	
	private long cep;
	
	private String complemento;
	
}
