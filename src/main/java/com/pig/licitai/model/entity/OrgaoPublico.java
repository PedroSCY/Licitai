package com.pig.licitai.model.entity;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class OrgaoPublico extends Usuario{
	
	private ArrayList<AnuncioLicitacao> anuncioLicitacao;

}
