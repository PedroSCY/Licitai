package com.pig.licitai.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaAcessoDTO {

	private String email;
	private boolean isAdmin;
	private String senha;
	
}
