package com.pig.licitai.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.pig.licitai.model.util.Atividade;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity(name = "TB_FORNECEDOR")
@NoArgsConstructor
public class Fornecedor extends Usuario {
	
	@Column(name = "TESTE")
	private String teste;
	
//	private Atividade atividade;

	
	public String toString() {
		return "Fornecedor {"
				+ "\n	" + getNome() 
				+ "\n	" + getCnpj()
				+ "\n	" + getTelefone()
//				+ "\n	" + getAtividade().toString()
				+ "\n	" + getContaAcesso().toString();
//				+ "\n	" + getEndereco().toString() + " }";
	}
	
	
}
