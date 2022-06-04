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
public class ContaAcesso {

	private String email;
	
	private String senha;
	
	private boolean isAdmin;
	
}
