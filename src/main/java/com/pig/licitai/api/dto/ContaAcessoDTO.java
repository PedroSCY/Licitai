package com.pig.licitai.api.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaAcessoDTO {

	private String email;
	private boolean isAdmin;
	private String senha;
	
}
