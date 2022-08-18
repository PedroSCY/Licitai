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
public class AnuncioDTO {

	private String text;
	private String titulo;
	private Atividade atividade;
	private String tipoEnvio;
}
