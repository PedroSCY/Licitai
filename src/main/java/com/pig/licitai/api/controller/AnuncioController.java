package com.pig.licitai.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pig.licitai.api.dto.AnuncioDTO;
import com.pig.licitai.exceptions.RegraDeNegocioException;
import com.pig.licitai.model.entity.AnuncioLicitacao;
import com.pig.licitai.model.util.GerenciadorNotificacao;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/anuncios")
@RequiredArgsConstructor
public class AnuncioController {
	
	private final GerenciadorNotificacao notificador;

	@PostMapping
	public ResponseEntity cadastrarAnuncio( @RequestBody AnuncioDTO dto) {
		try {
			AnuncioLicitacao anuncioLicitacao = new AnuncioLicitacao();
			BeanUtils.copyProperties(dto, anuncioLicitacao);
			
			notificador.LancarAnuncioLicitacao(anuncioLicitacao, dto.getTipoEnvio());
			return new ResponseEntity<>(anuncioLicitacao, HttpStatus.CREATED);
		} catch (RegraDeNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
