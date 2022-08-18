package com.pig.licitai.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Entity
@Table(name = "TB_ATIVIDADE")
public class Atividade {
	
	@Id
	@Column(name = "CODIGO_Atividade")
	private String codigoAtividade;

	@Column(name = "SIGLA_SEGMENTO")
	private String siglaSegmento;

	@Column(name = "NOME_Atividade")
	private String nomeAtividade;
	
}
