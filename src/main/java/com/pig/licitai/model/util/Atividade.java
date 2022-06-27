package com.pig.licitai.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
//@Embeddable
public class Atividade {

	@Column(name = "nomeAtividade")
	private String nomeAtividade;
	
	@Column(name = "codigoAtividade")
	private String codigoAtividade;
	
}
