package com.pig.licitai.model.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.pig.licitai.model.util.Atividade;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity(name = "TB_FORNECEDOR")
@NoArgsConstructor
public class Fornecedor extends Usuario {
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ATIVIDADE_FK", nullable = false)
	private Atividade atividade;
	
	public String toString() {
		return "Fornecedor {"
				+ "\n	" + getNome() 
				+ "\n	" + getCnpj()
				+ "\n	" + getTelefone()
				+ "\n	" + getAtividade().toString()
				+ "\n	" + getContaAcesso().toString()
				+ "\n	" + getEndereco().toString() + " }";
	}
	
	
}
