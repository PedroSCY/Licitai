package com.pig.licitai.model.entity;

import com.pig.licitai.model.util.Atividade;
import com.pig.licitai.model.util.ContaAcesso;
import com.pig.licitai.model.util.Endereco;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Fornecedor extends Usuario {
	
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
