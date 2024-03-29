package com.pig.licitai.model.entity;

import java.time.LocalDateTime;

import com.pig.licitai.model.enuns.StatusEmail;
import com.pig.licitai.model.util.Atividade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioLicitacao {

	private String text;
	
	private String titulo;
	
	private Atividade Atividade;
}
